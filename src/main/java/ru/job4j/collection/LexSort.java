package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] l = left.split("\\.");
        String[] r = right.split("\\.");
        int lc = 0;
        int rc = 0;
        while (lc < l.length && rc < r.length) {
            int li, ri;
            try {
                li = Integer.parseInt(l[lc]);
                lc++;
            } catch (NumberFormatException e) {
                break;
            }
            try {
                ri = Integer.parseInt(r[rc]);
                rc++;
            } catch (NumberFormatException e) {
                break;
            }
            if (li != ri) {
                return Integer.compare(li, ri);
            }
        }
        return Integer.compare(lc, rc);
    }
}
