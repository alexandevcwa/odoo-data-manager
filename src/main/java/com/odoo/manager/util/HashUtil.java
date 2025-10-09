package com.odoo.manager.util;

import java.nio.file.Path;
import java.nio.file.Files;
import java.security.MessageDigest;

public class HashUtil {

    /**
     * Calculate SHA-256 hash of a file
     * @param file File path
     * @return SHA-256 hash as a hex string
     * @throws Exception if an error occurs
     */
    public static String sha256(Path file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (var is = Files.newInputStream(file)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = is.read(buffer)) != -1) {
                md.update(buffer, 0, read);
            }
        }
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}