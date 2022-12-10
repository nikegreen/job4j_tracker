package ru.job4j.early;

import static java.lang.Character.*;

public class PasswordValidator {

    public static String validate(String password) throws IllegalArgumentException {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() > 32 || password.length() < 8) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        boolean digit = true;
        boolean upperCase = true;
        boolean lowCase = true;
        boolean special = true;
        for (char ch:password.toCharArray()) {
            if (isUpperCase(ch)) {
                upperCase = false;
            }
            if (isLowerCase(ch)) {
                lowCase = false;
            }
            if (isDigit(ch)) {
                digit = false;
            }
            if (!isDigit(ch) && !isAlphabetic(ch)) {
                special = false;
            }
        }
        if (upperCase) {
            throw new IllegalArgumentException(
                    "Password should contain at least one uppercase letter");
        }
        if (lowCase) {
            throw new IllegalArgumentException(
                    "Password should contain at least one lowercase letter");
        }
        if (digit) {
            throw new IllegalArgumentException(
                    "Password should contain at least one figure");
        }
        if (special) {
            throw new IllegalArgumentException(
                    "Password should contain at least one special symbol");
        }
        if (findSubstrings(password)) {
            throw new IllegalArgumentException(
                    "Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }

    private static boolean findSubstrings(String password) {
        String[] subStrings = {"qwerty", "12345", "password", "admin", "user" };
        password = password.toLowerCase();
        for (String str : subStrings) {
            if (password.contains(str)) {
                return true;
            }
        }
        return false;
    }
}
