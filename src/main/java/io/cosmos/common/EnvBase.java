package io.cosmos.common;

public class EnvBase {

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
        return  "http://localhost:10259";
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

    public String GetNode5Mnmonic() {
        return "clerk universe city game fortune kitchen arrive regret donor wide borrow typical hold harbor actor raise inside truly nation ethics rally layer arena clump";
    };

    public String GetNode5Addr() {
        return "eva1hxtpykp4x8u99hqkq5mayea5tgdpehcxy4v2k5";
    };

    public String GetNode1Addr() {
        return "eva1geyy4wtak2q9effnfhze9u4htd8yxxma0jmgl6";
    };
    public String GetTransferAmount() {
        return "160" + "000000000";
    };


    public String GetTendermintConsensusPubkey() {
        return "evavalconspub1zcjduepqc4z8pwqsvdh0pectjzuh5s0jaata266x8cf7ka2styqzkqrmq8qsyzctcg";
    };


}
