package io.cosmos.msg;

import io.cosmos.crypto.Crypto;
import io.cosmos.msg.delegate.*;
import io.cosmos.types.*;
import io.cosmos.util.EncodeUtils;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MsgDelegate extends MsgBase {

    protected String validatorAddress;

    public static void main(String[] args) {
        MsgDelegate msg = new MsgDelegate();

        msg.setMsgType("cosmos-sdk/MsgDelegate");
        msg.setValidatorAddress("cosmosvaloper1l25rrm7vrmzgqcplp5s7c8edeljxt78gmuqs0a");

        msg.submit("stake",
                "3",
                "200000",
                "testchain",
                "stake",
                "100",
                "2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");
    }

    public void setValidatorAddress(String addr) {
        this.validatorAddress = addr;
    }

    protected MessageDelegateMulti[] produceDelegateMsg(String delegateDenom, String delegateAmount) {

        MsgDelegateValue delegateValue = new MsgDelegateValue();
        delegateValue.setValidatorAddress(validatorAddress);
        delegateValue.setDelegatorAddress(address);
        //amount
        Token token = new Token();
        token.setDenom(delegateDenom);
        token.setAmount(delegateAmount);
        delegateValue.setAmount(token);
        MessageDelegateMulti<MsgDelegateValue> messageDelegateMulti = new MessageDelegateMulti<>();
        messageDelegateMulti.setType(msgType);
        messageDelegateMulti.setValue(delegateValue);
        MessageDelegateMulti[] msgs = new MessageDelegateMulti[1];
        msgs[0]=messageDelegateMulti;
        return msgs;
    }


    public void submit(String feeDenom,
                       String feeAmount,
                       String gas,
                       String chainId,
                       String delegateDenom,
                       String delegateAmount,
                       String privateKey) {

        String memo = "cosmos delegate";
        String mode ="block";
        String tranType = "auth/StdTx";

        init(privateKey);

        byte[] priKeyVal = Hex.decode(privateKey);
        byte[] pubKeyVal = Hex.decode(this.pubKeyString);

        //fee
        Token amount = new Token();
        amount.setDenom(feeDenom);
        amount.setAmount(feeAmount);
        Fee fee = new Fee();
        fee.setAmount(Collections.singletonList(amount));
        fee.setGas(gas);

        //accountList
        CosmosAccount account = new CosmosAccount(address, accountNum, sequenceNum);
        List<CosmosAccount> accountList = Collections.singletonList(account);
        //make signData
        CosmosSignData cosmosSignData = new CosmosSignData(chainId, fee, memo, accountList);

        MessageDelegateMulti[] msgs = produceDelegateMsg(delegateDenom, delegateAmount);

        //整个json
        CosmosDelegateData cosmosDelegateData = new CosmosDelegateData();
        //signData
        cosmosDelegateData.setSignData(cosmosSignData);
        //memo
        cosmosDelegateData.setMode(mode);
        //tx
        CosmosDelegateMulti delegateMulti = new CosmosDelegateMulti();
        cosmosDelegateData.setTx(delegateMulti);
        delegateMulti.setType(tranType);
        delegateMulti.setMemo(memo);
        delegateMulti.setFee(fee);
        delegateMulti.setMsgs(msgs);
        //make sign
        List<Signature> signatures = makeSign(cosmosSignData,accountList,msgs,priKeyVal,pubKeyVal);
        delegateMulti.setSignatures(signatures);

        boardcast(cosmosDelegateData.toJson());
    }

    //签名组装交易
    protected static List<Signature> makeSign(CosmosSignData cosmosSignData,List<CosmosAccount> accountList ,
                                           MessageDelegateMulti[] msgs,byte[] priKeyVal,byte[] pubKeyVal) {
        List<Signature> signatures = new LinkedList<>();
        try {
            for (int i = 0; i < accountList.size(); i++) {
                final CosmosAccount cosmosAccount = accountList.get(i);

                SignDataDelegateMulti signData = new SignDataDelegateMulti(cosmosAccount.getAccountNumber(),
                        cosmosSignData.getChainId(),
                        cosmosSignData.getFee(),
                        cosmosSignData.getMemo(),
                        msgs,
                        cosmosAccount.getSequence());
                //序列化
                byte[] byteSignData = EncodeUtils.toJsonEncodeBytes(signData);
                //签名
                byte[] sig = Crypto.sign(EncodeUtils.hexStringToByteArray(EncodeUtils.bytesToHex(byteSignData)),
                        Hex.toHexString(priKeyVal));
                String sigResult = Strings.fromByteArray(Base64.encode(sig));
                System.out.println("Sign result: " + sigResult);    //expected: /hRJwaM+SH0kLb8x9Uy4TnSeSlnyYzUGKaERQF1ABmMOXA56l7NhphVUISAeyQx06pMHbipaPq4gr6++SCVtpg==
                Signature signature = new Signature();
                Pubkey pubkey = new Pubkey(Strings.fromByteArray(Base64.encode(pubKeyVal)));
                signature.setPubkey(pubkey);
                signature.setSignature(sigResult);
                signatures.add(signature);
            }
        } catch (Exception e) {
            System.out.println( "sign failed!");
            System.out.println(e);
        }
        return signatures;
    }




}
