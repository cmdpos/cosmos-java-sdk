package io.cosmos.msg.delegate;

import io.cosmos.common.Utils;
import io.cosmos.types.CosmosSignData;


public class CosmosDelegateData {

    private CosmosSignData signData;

    private String mode;

    private CosmosDelegateMulti tx;

    public CosmosDelegateData() {
    }

    public CosmosDelegateData(CosmosSignData signData, CosmosDelegateMulti tx) {
        this.signData = signData;
        this.tx = tx;
    }

    public void setSignData(CosmosSignData signData) {
        this.signData = signData;
    }

    public CosmosSignData getSignData() {
        return signData;
    }

    public CosmosDelegateMulti getTx() {
        return tx;
    }

    public void setTx(CosmosDelegateMulti tx) {
        this.tx = tx;
    }

    public String toJson() {
        return Utils.serializer.toJson(this);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public static CosmosDelegateData fromJson(String json) {
        return Utils.serializer.fromJson(json, CosmosDelegateData.class);
    }
}
