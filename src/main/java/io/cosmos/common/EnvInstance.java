package io.cosmos.common;

public class EnvInstance {

    static Env prodEnv = new ProdEnv();
    static Env localEnv = new LocalEnv();
    static Env okEnv = new OKEnv();

    static public Env getEnv() {
        String type = "okq";

        if (type == "ok") {
            return okEnv;
        } else if (type == "prod") {
            return prodEnv;
        }

        return localEnv;
    }
}
