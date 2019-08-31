package io.cosmos.msg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.cosmos.common.Constants;
import io.cosmos.common.HttpUtils;
import io.cosmos.crypto.Crypto;
import org.bouncycastle.util.encoders.Hex;

public class MsgBase {

    protected String sequenceNum;
    protected String accountNum;
    protected String pubKeyString;
    protected String address;
    protected String msgType;

    public void setMsgType(String type) {
        this.msgType = type;
    }

    void init(String privateKey) {
        pubKeyString = Hex.toHexString(Crypto.generatePubKeyFromPriv(privateKey));
        address = Crypto.generateAddressFromPriv(privateKey);
        JSONObject accountJson = JSON.parseObject(getAccountPrivate(address));
        sequenceNum = getSequance(accountJson);
        accountNum = getAccountNumber(accountJson);
    }

    private String getAccountPrivate(String userAddress) {
        String url = Constants.REST_URL + Constants.COSMOS_ACCOUNT_URL_PATH + userAddress;
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
        String res = HttpUtils.httpPost(Constants.REST_URL + Constants.TRANSACTION_URL_PATH, tx);
        JSONObject result = JSON.parseObject(res);

        System.out.println(result);
        return result;
    }
}
