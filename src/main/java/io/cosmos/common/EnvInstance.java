package io.cosmos.common;

public class EnvInstance {

    static Env prodEnv = new ProdEnv();
    static Env localEnv = new LocalEnv();
    static Env okEnv = new OKEnv();

    static Env env = localEnv;

    static public void setEnv(String type) {

        if (type == "ok") {
            env = okEnv;
        } else if (type == "prod") {
            env = prodEnv;
        } else {
            env = localEnv;
        }
    }

    static public Env getEnv() {
        return env;

//        String type = "ok";
//
//        if (type == "ok") {
//            return okEnv;
//        } else if (type == "prod") {
//            return prodEnv;
//        }
//
//        return localEnv;
    }
}
