package io.cosmos.common;

public class Env {

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
        return  "http://localhost:10059";
    }

    public String GetHDPath(){
        return "M/44H/118H/0H/0/0";
    }


    public String GetValidatorAddrPrefix(){
        return  "evavaloper";
    }


    public String GetRestPathPrefix() {
        return "";
    };
    public boolean HasFee() {
        return true;
    };


    public String GetTxUrlPath() {
        return "/txs";
    };


    public String GetNode0Mnmonic() {
        return "depart neither they audit pen exile fire smart tongue express blanket burden culture shove curve address together pottery suggest lady sell clap seek whisper";
    };
    public String GetNode1Mnmonic() {
        return "country live width exotic behind mad belt bachelor later outside forget rude pudding material orbit shoot kind curve endless prosper make exotic welcome maple";
    };

    public String GetNode0Addr() {
        return "eva1hg40dv5e237qy28vtyum52ygke32ez35syykpz";
    };
    public String GetNode0ValAddr() {
        return "evavaloper1hg40dv5e237qy28vtyum52ygke32ez35xexq98";
    };


    public String GetNode1Addr() {
        return "eva1geyy4wtak2q9effnfhze9u4htd8yxxma0jmgl6";
    };



}
