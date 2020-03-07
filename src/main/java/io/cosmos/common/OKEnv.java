package io.cosmos.common;

public class OKEnv extends Env {

    public String GetMainPrefix(){
        return "okchain";
    };
    public String GetDenom(){
        return "tokt";
    }
    public String GetChainid(){
        return "okchain";
    }

    public String GetRestServerUrl(){
        return  "http://localhost:26659";
    }

    public String GetHDPath(){
        return "M/44H/996H/0H/0/0";
    }

    public String GetValidatorAddrPrefix(){
        return  "okchainvaloper";
    }

    public String GetRestPathPrefix(){
        return  "/okchain/v1";
    }

    public String GetTxUrlPath() {
        return "/okchain/v1/txs";
    };

    public boolean HasFee() {
        return false;
    };
}
