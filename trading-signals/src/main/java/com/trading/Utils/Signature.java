package com.trading.Utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Signature {
    public static String sign(String data, String secret) {
        try {
            Mac sha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256.init(key);
            byte[] hash = sha256.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(byte b: hash) {
                sb.append(String.format("%02x", b));
            }
            String signature = sb.toString();
            return signature;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
