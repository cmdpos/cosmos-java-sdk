package io.cosmos.common;

public class ProdEnv implements Env {

    public String GetMainPrefix(){
        return "eva";
    };
    public String GetDenom(){
        return "neva";
    }
    public String GetChainid(){
        return "evaio";
    }
}
