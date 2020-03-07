package io.cosmos.common;

public class LocalEnv extends Env {

    public String GetMainPrefix(){
        return "eva";
    };
    public String GetDenom(){
        return "neva";
    }
    public String GetChainid(){
        return "testchain";
    }


    public String GetRestServerUrl(){

//        return  "http://localhost:26659";
        return  "http://localhost:10059";
    }


    public String GetHDPath(){
        return "M/44H/118H/0H/0/0";
    }


    public String GetValidatorAddrPrefix(){
        return  "okchainvaloper";
    }
}
