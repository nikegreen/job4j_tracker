package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("parsentev@yandex.ru", "Petr Arsentev");
        map.put("ivanov@mail.ru", "Ivanov Ivan");
        map.put("sidorov@gmail.com", "Sidorov Michail");
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }
    }
}
