package io.cosmos.msg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.cosmos.common.Constants;
import io.cosmos.common.EnvInstance;
import io.cosmos.common.HttpUtils;
import io.cosmos.crypto.Crypto;
import io.cosmos.msg.utils.BoardcastTx;
import io.cosmos.msg.utils.Data2Sign;
import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.TxValue;
import io.cosmos.types.Fee;
import io.cosmos.types.Pubkey;
import io.cosmos.types.Signature;
import io.cosmos.types.Token;
import io.cosmos.util.EncodeUtils;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.util.ArrayList;
import java.util.List;

public class MsgBase {

//    protected String restServerUrl = "http://localhost:1317";
    protected String restServerUrl = "https://stargate.evaio.net";

    protected String sequenceNum;
    protected String accountNum;
    protected String pubKeyString;
    protected String address;
    protected String priKeyString;

    static protected String msgType;

    public void setMsgType(String type) {
        this.msgType = type;
    }

    public void setRestServerUrl(String restServerUrl) {
        this.restServerUrl = restServerUrl;
    }


    static Signature sign(Object data, String privateKey) {
        Signature signature = new Signature();

        try {
            //序列化
            byte[] byteSignData = EncodeUtils.toJsonEncodeBytes(data);

            // sign
            byte[] sig = Crypto.sign(EncodeUtils.hexStringToByteArray(EncodeUtils.bytesToHex(byteSignData)), privateKey);

            String sigResult = Strings.fromByteArray(Base64.encode(sig));

            //组装签名结构
            Pubkey pubkey = new Pubkey();
            pubkey.setType("tendermint/PubKeySecp256k1");
            String pubKeyString = Hex.toHexString(Crypto.generatePubKeyFromPriv(privateKey));
            pubkey.setValue(Strings.fromByteArray(Base64.encode(Hex.decode(pubKeyString))));
            signature.setPubkey(pubkey);
            signature.setSignature(sigResult);

        } catch (Exception e) {
            System.out.println("serialize msg failed");
        }

        return signature;
    }


    void initMnemonic(String mnemonic) {
        String prikey = Crypto.generatePrivateKeyFromMnemonic(mnemonic);
        init(prikey);
    }

    void init(String privateKey) {
        pubKeyString = Hex.toHexString(Crypto.generatePubKeyFromPriv(privateKey));
        address = Crypto.generateAddressFromPriv(privateKey);
        JSONObject accountJson = JSON.parseObject(getAccountPrivate(address));
        sequenceNum = getSequance(accountJson);
        accountNum = getAccountNumber(accountJson);
        priKeyString = privateKey;
    }

    private String getAccountPrivate(String userAddress) {
        String url = restServerUrl + Constants.COSMOS_ACCOUNT_URL_PATH + userAddress;
        System.out.println(url);
        return HttpUtils.httpGet(url);
    }

    private String getSequance(JSONObject account) {
        String res = (String) account.getJSONObject("value").get("sequence");
        return res;
    }

    private String getAccountNumber(JSONObject account) {
        String res = (String) account.getJSONObject("value").get("account_number");
        return res;
    }

    protected JSONObject boardcast(String tx) {
        System.out.println("Boardcast tx:");
        System.out.println(tx);

        System.out.println("Response:");
        String res = HttpUtils.httpPost(restServerUrl + Constants.COSMOS_TRANSACTION_URL_PATH, tx);
        JSONObject result = JSON.parseObject(res);

        System.out.println(result);
        System.out.println("------------------------------------------------------");
        return result;
    }


    public void submit(Message message,
                       String feeAmount,
                       String gas,
                       String memo) {
        try {
            List<Token> amountList = new ArrayList<>();
            Token amount = new Token();
            amount.setDenom(EnvInstance.env.GetDenom());
            amount.setAmount(feeAmount);
            amountList.add(amount);

            //组装待签名交易结构
            Fee fee = new Fee();
            fee.setAmount(amountList);
            fee.setGas(gas);


            Message[] msgs = new Message[1];
            msgs[0] = message;

            Data2Sign signData = new Data2Sign(accountNum, EnvInstance.env.GetChainid(), fee, memo, msgs, sequenceNum);
            List<Signature> signatureList = new ArrayList<>();
            Signature signature = MsgBase.sign(signData, priKeyString);
            signatureList.add(signature);

            BoardcastTx cosmosTransaction = new BoardcastTx();
            cosmosTransaction.setMode("block");

            TxValue cosmosTx = new TxValue();
            cosmosTx.setType("auth/StdTx");
            cosmosTx.setMsgs(msgs);
            cosmosTx.setFee(fee);
            cosmosTx.setMemo(memo);
            cosmosTx.setSignatures(signatureList);
            cosmosTransaction.setTx(cosmosTx);

            boardcast(cosmosTransaction.toJson());
        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }
}
