package io.cosmos.common;

public class ProdEnv extends EnvBase {

    public String GetMainPrefix(){
        return "eva";
    };
    public String GetDenom(){
        return "neva";
    }
    public String GetChainid(){
        return "evaio";
    }

    public String GetRestServerUrl(){
        return  "https://stargate.evaio.net";
    }



    public String GetHDPath(){
        return "M/44H/118H/0H/0/0";
    }


    public String GetValidatorAddrPrefix(){
        return  "okchainvaloper";
    }
}
