package com.odoo.manager.util;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class HashUtil {

    /**
     * Calculate SHA-256 hash of a file
     *
     * @param file File path
     * @return SHA-256 hash as a hex string
     * @throws Exception if an error occurs
     */
    private static String sha256(Path file) throws Exception {
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

    /**
     * Obtain SHA-256 hash of a file given its path
     * @param filePath File path
     * @return SHA-256 hash as a hex string
     * @throws RuntimeException if file not found or error occurs
     */
    public static String obtainSHA256(String filePath) {
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            throw new RuntimeException("File not found: " + filePath);
        } else if (Files.isDirectory(path)) {
            throw new RuntimeException("Directory found: " + filePath);
        } else if (Files.isRegularFile(path)) {
            try {
                return sha256(path);
            } catch (Exception e) {
                throw new RuntimeException("Error calculating SHA-256: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Invalid file: " + filePath);
        }
    }
}