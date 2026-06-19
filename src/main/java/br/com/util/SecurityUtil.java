package br.com.util;

import com.password4j.Password;

import java.security.SecureRandom;
import java.util.Base64;

public final class SecurityUtil {
    private static final SecureRandom RANDOM = new SecureRandom();

    private SecurityUtil() {}

    public static String hashPassword(String plainPassword) {
        return Password.hash(plainPassword).addRandomSalt().withArgon2().getResult();
    }

    public static boolean verifyPassword(String plainPassword, String storedHash) {
        return Password.check(plainPassword, storedHash).withArgon2();
    }

    public static String generateToken() {
        byte[] bytes = new byte[32];
        RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String hashToken(String token) {
        // Usa Argon2 também para não salvar token puro
        return Password.hash(token).addRandomSalt().withArgon2().getResult();
    }

    public static boolean verifyToken(String token, String tokenHash) {
        return Password.check(token, tokenHash).withArgon2();
    }
}