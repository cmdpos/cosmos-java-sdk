package io.cosmos.api;

import io.cosmos.util.AddressUtil;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;


public class AddressTest {

    @Test
    public void testCreateAddress() {
        String mainPrefix = "cosmos";
        String pubKey = "02141202d82740af1b754b442f5fd9c4a862d49b5cafca5d04a9f06216ddf545bd";
        try {
            System.out.println(AddressUtil.createNewAddressSecp256k1(mainPrefix, Hex.decode(pubKey)));
            //expected: cosmos1p0w9dwkgteyd8298tn3sed32g5q9ny7x62ejv4
        } catch (Exception e) {
            System.out.println("create cosmos address exception");
        }
    }
}
