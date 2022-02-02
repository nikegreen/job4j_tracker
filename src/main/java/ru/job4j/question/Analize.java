package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> map = previous.stream()
                .collect(Collectors.toMap(value -> value.getId(),
                        value -> value));
        int add = 0;
        int cng = 0;
        for (User u : current) {
            if (map.containsKey(u.getId())) {
                if (!map.get(u.getId()).getName().equals(u.getName())) {
                    cng++;
                }
                map.remove(u.getId());
            } else {
                add++;
            }
        }
        int del = map.size();
        return new Info(add, cng, del);
    }

}
