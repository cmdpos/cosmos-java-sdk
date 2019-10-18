package io.cosmos.types;

import io.cosmos.common.ParameterizedTypeImpl;
import io.cosmos.common.SuccessRespon;
import io.cosmos.common.Utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
public class CosmosTransaction {

    private String mode;

    private CosmosValue tx;

    public CosmosTransaction() {

    }
    public CosmosTransaction(String type, CosmosValue tx, String mode) {
        this.tx = tx;
        this.mode = mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getMode() {
        return mode;
    }

    public void setTx(CosmosValue tx) {
        this.tx = tx;
    }

    public CosmosValue getTx() {
        return tx;
    }

    public String toJson() {
        return Utils.serializer.toJson(this);
    }

    public static CosmosTransaction fromJson(String json) {
        return Utils.serializer.fromJson(json, CosmosTransaction.class);
    }

    public static CosmosTransaction fromSuccessRespon(String json) {
        Type responType = new ParameterizedTypeImpl(SuccessRespon.class, new Class[]{CosmosTransaction.class});
        SuccessRespon<CosmosTransaction> result = Utils.serializer.fromJson(json, responType);
        return result.dataObject;
    }
}
