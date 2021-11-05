package ru.job4j.lambda;

import java.util.Arrays;
import java.util.List;

public class LambdaUsage {
    public static void main(String[] args) {
        String[] numbers = {
                "1. Task",
                "2. Task",
                "11. Task"
        };
        List<String> list = Arrays.asList(numbers);
        System.out.println(list);
        list.sort((l, r) -> {
            int res = r.compareTo(l);
            System.out.println("Compare: " + l + " и " + r + " =" + res);
            return res;
        });
        System.out.println(list);
    }
}
