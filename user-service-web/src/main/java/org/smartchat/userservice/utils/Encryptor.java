package org.smartchat.userservice.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
    private Encryptor() {
    }

    public static String getHash(String text) {
        String salt = "!0nSalt";
        String passWithSalt = salt+text;
        String encryptedText = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(passWithSalt.getBytes());
            byte[] encodedHash = digest.digest();

            encryptedText= DatatypeConverter.printHexBinary(encodedHash).toUpperCase();
        } catch (NoSuchAlgorithmException e) {

        }

        return encryptedText;
    }
}
// 317ED18A9D35EC8D617D77DBED20B71054BF4A15EDF2DE9C11CFEC37AE1823D5