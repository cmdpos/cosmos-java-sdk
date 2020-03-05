package io.cosmos.msg;

import io.cosmos.crypto.Crypto;
import io.cosmos.types.*;
import io.cosmos.util.EncodeUtils;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.util.ArrayList;
import java.util.List;

public class MsgSend extends MsgBase {

    public static void main(String[] args) {
        MsgSend msg = new MsgSend();

        msg.setMsgType("cosmos-sdk/MsgSend");
//        msg.setRestServerUrl("http://localhost:1317");

        msg.submit("neva",
                "6",
                "200000",
                "evaio",
                "neva",
                "16",
                "eva1pn80qt83wzk9w4gs3muc8hw26cexlgav75mar0",
                "cosmos transfer",
                "6dd0081f36e66121e8140ff85648d28dc0e01cb337a15a6f965e742b815ffdac"
//                "9357d721b9bec0c5c0983763428222c8ce4413e14a97b2c7c6ff121809cf67ab"
        );
    }

    public void submit(String feeDenom, String feeAmount, String gas,
                         String chainId,
                         String transferDenom,
                         String transferAmount,
                       String to,
                       String memo,
                         String privateKey) {
        try {
            init(privateKey);
            List<Token> amountList = new ArrayList<>();
            Token amount = new Token();
            amount.setDenom(feeDenom);
            amount.setAmount(feeAmount);
            amountList.add(amount);

            //组装待签名交易结构
            TransferMessage transferMessage = newMsgSend(transferDenom, transferAmount, address, to);

            Fee fee = new Fee();
            fee.setAmount(amountList);
            fee.setGas(gas);

            SignData signData = new SignData();
            signData.setAccountNumber(accountNum);
            signData.setChainId(chainId);
            signData.setFee(fee);
            signData.setMemo(memo);
            signData.setMsgs(new TransferMessage[] {transferMessage});
            signData.setSequence(sequenceNum);

            System.out.println("Msg to be signed:");
            System.out.println(signData.toJson());
            //序列化
            byte[] byteSignData = EncodeUtils.toJsonEncodeBytes(signData);

            // sign
            byte[] sig = Crypto.sign(EncodeUtils.hexStringToByteArray(EncodeUtils.bytesToHex(byteSignData)), privateKey);

            String sigResult = Strings.fromByteArray(Base64.encode(sig));

            System.out.println("Signature:");
            System.out.println(sigResult);

            //组装待广播交易结构
            CosmosTransaction cosmosTransaction = new CosmosTransaction();
            cosmosTransaction.setMode("block");
            CosmosValue cosmosValue = new CosmosValue();
            cosmosValue.setMsgs(new TransferMessage[] {transferMessage});
            cosmosValue.setFee(fee);

            //组装签名结构
            List<Signature> signatures = new ArrayList<>();
            Signature signature = new Signature();
            Pubkey pubkey = new Pubkey();
            pubkey.setType("tendermint/PubKeySecp256k1");

            String pubKeyString = Hex.toHexString(Crypto.generatePubKeyFromPriv(privateKey));
            pubkey.setValue(Strings.fromByteArray(Base64.encode(Hex.decode(pubKeyString))));
            signature.setPubkey(pubkey);
            signature.setSignature(sigResult);
            signatures.add(signature);

            cosmosValue.setMemo(memo);
            cosmosValue.setSignatures(signatures);
            cosmosTransaction.setTx(cosmosValue);

            boardcast(cosmosTransaction.toJson());
        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }


    private TransferMessage newMsgSend(String denom, String amountDenom, String from, String to) {
        TransferMessage transferMessage = new TransferMessage();

        List<Token> amountList = new ArrayList<>();
        Token amount = new Token();
        amount.setDenom(denom);
        amount.setAmount(amountDenom);
        amountList.add(amount);

        Value value = new Value();
        value.setFromAddress(from);
        value.setToAddress(to);
        value.setAmount(amountList);

        transferMessage.setType(this.msgType);
        transferMessage.setValue(value);
        return transferMessage;
    }

}
