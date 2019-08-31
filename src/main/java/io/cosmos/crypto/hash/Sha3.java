package io.cosmos.crypto.hash;

import org.bouncycastle.crypto.digests.SHA3Digest;

public class Sha3 {

    public static byte[] sha3224(byte[]... args) {
        SHA3Digest digest = new SHA3Digest(224);
        for (byte[] bytes : args) {
            digest.update(bytes, 0, bytes.length);
        }
        byte[] out = new byte[224 / 8];
        digest.doFinal(out, 0);
        return out;
    }

    public static byte[] sha3256(byte[]... args) {
        SHA3Digest digest = new SHA3Digest(256);
        for (byte[] bytes : args) {
            digest.update(bytes, 0, bytes.length);
        }
        byte[] out = new byte[256 / 8];
        digest.doFinal(out, 0);
        return out;
    }



    public static byte[] sha3384(byte[]... args) {
        SHA3Digest digest = new SHA3Digest(384);
        for (byte[] bytes : args) {
            digest.update(bytes, 0, bytes.length);
        }
        byte[] out = new byte[384 / 8];
        digest.doFinal(out, 0);
        return out;
    }

    public static byte[] sha3512(byte[]... args) {
        SHA3Digest digest = new SHA3Digest(512);
        for (byte[] bytes : args) {
            digest.update(bytes, 0, bytes.length);
        }
        byte[] out = new byte[512 / 8];
        digest.doFinal(out, 0);
        return out;
    }
}
