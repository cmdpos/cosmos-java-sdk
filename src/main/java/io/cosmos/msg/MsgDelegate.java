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
        msg.setValidatorAddress("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083");

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

    protected Messages[] produceDelegateMsg(String delegateDenom, String delegateAmount) {

        MsgDelegateValue delegateValue = new MsgDelegateValue();
        delegateValue.setValidatorAddress(validatorAddress);
        delegateValue.setDelegatorAddress(address);
        //amount
        Token token = new Token();
        token.setDenom(delegateDenom);
        token.setAmount(delegateAmount);
        delegateValue.setAmount(token);
        Messages<MsgDelegateValue> messageDelegateMulti = new Messages<>();
        messageDelegateMulti.setType(msgType);
        messageDelegateMulti.setValue(delegateValue);
        Messages[] msgs = new Messages[1];
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
//
        Messages[] msgs = produceDelegateMsg(delegateDenom, delegateAmount);

        //整个json
        CosmosTransaction cosmosTransaction = new CosmosTransaction();

        BoardcastTx cosmosDelegateData = new BoardcastTx();
        //memo
        cosmosDelegateData.setMode(mode);
        //tx
        TxValue cosmosTx = new TxValue();
        cosmosTx.setType(tranType);
        cosmosTx.setMemo(memo);
        cosmosTx.setFee(fee);
        cosmosTx.setMsgs(msgs);
        //make sign
        List<Signature> signatures = makeSign(cosmosSignData, accountList, msgs, privateKey);
        cosmosTx.setSignatures(signatures);


        cosmosDelegateData.setTx(cosmosTx);
        boardcast(cosmosDelegateData.toJson());
    }

    //签名组装交易
    protected static List<Signature> makeSign(CosmosSignData cosmosSignData,
                                              List<CosmosAccount> accountList,
                                              Messages[] msgs, String priKeyVal) {
        List<Signature> signatureList = new LinkedList<>();
        try {
            for (int i = 0; i < accountList.size(); i++) {
                final CosmosAccount cosmosAccount = accountList.get(i);

                SignDataDelegateMulti signData = new SignDataDelegateMulti(cosmosAccount.getAccountNumber(),
                        cosmosSignData.getChainId(),
                        cosmosSignData.getFee(),
                        cosmosSignData.getMemo(),
                        msgs,
                        cosmosAccount.getSequence());

                Signature signature = MsgBase.sign(signData, priKeyVal);
                signatureList.add(signature);
            }
        } catch (Exception e) {
            System.out.println( "sign failed!");
            System.out.println(e);
        }
        return signatureList;
    }




}
