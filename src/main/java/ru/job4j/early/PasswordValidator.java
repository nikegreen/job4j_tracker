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
        if (!findUpperCase(password)) {
            throw new IllegalArgumentException(
                    "Password should contain at least one uppercase letter");
        }
        if (!findLowCase(password)) {
            throw new IllegalArgumentException(
                    "Password should contain at least one lowercase letter");
        }
        if (!findDigit(password)) {
            throw new IllegalArgumentException(
                    "Password should contain at least one figure");
        }
        if (!findSpecial(password)) {
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

    private static boolean findSpecial(String password) {
        for (char ch:password.toCharArray()) {
            if (!isDigit(ch) && !isAlphabetic(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean findDigit(String password) {
        for (char ch:password.toCharArray()) {
            if (isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean findLowCase(String password) {
        for (char ch:password.toCharArray()) {
            if (isLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean findUpperCase(String password) {
        for (char ch:password.toCharArray()) {
            if (isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
