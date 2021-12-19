package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] oo1 = o1.split("/");
        String[] oo2 = o2.split("/");
        int len = Math.min(oo1.length, oo2.length);
        int count = 0;
        while (count < len) {
            int res = oo1[count].compareTo(oo2[count]);
            if (res != 0) {
                return res;
            }
            count++;
        }
        return Integer.compare(oo1.length, oo2.length);
    }
}
