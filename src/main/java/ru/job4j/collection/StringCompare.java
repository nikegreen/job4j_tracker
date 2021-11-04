package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int num = Math.min(left.length(), right.length());
        for (int i = 0; i < num; i++) {
            int res = Character.compare(left.charAt(i), right.charAt(i));
            if (res == 0) {
                continue;
            }
            return res;
        }
        return left.length() - right.length();
    }
}
