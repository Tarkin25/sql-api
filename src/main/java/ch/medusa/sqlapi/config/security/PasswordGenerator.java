package ch.medusa.sqlapi.config.security;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class PasswordGenerator {

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public String generatePassword(int length) {
        try {
            UUID uuid = UUID.randomUUID();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(uuid.toString().getBytes(StandardCharsets.UTF_8));

            return bytesToHex(digest.digest()).substring(0, length);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "12345";
    }



    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
