package personal_finance.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    
    public static String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        return toHexString(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8)));
    }

    public static String toHexString(byte[] hash) {
        BigInteger num = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(num.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        
        return hexString.toString();
    }

}
