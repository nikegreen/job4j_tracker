package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] l = left.split("\\.");
        String[] r = right.split("\\.");
        List<Integer> li = new ArrayList<>();
        List<Integer> ri = new ArrayList<>();
        for (String str : l) {
            try {
                int res = Integer.parseInt(str);
                li.add(res);
            } catch (NumberFormatException e) {
                break;
            }
        }
        for (String str : r) {
            try {
                int res = Integer.parseInt(str);
                ri.add(res);
            } catch (NumberFormatException e) {
                break;
            }
        }
        int min = Math.min(ri.size(), li.size());
        for (int i = 0; i < min; i++) {
            int res = Integer.compare(li.get(i), ri.get(i));
            if (res == 0) {
                continue;
            }
            return res;
        }
        return li.size() - ri.size();
    }
}
