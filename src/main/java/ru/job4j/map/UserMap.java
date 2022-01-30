package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserMap {
    public static void main(String[] args) {
        User user1 = new User("user1", 0, new GregorianCalendar(2017, 0, 25));
        User user2 = new User("user1", 0, new GregorianCalendar(2017, 0, 25));
        Map<User, Object> map1 = new HashMap<User, Object>();
        map1.put(user1, new Object());
        map1.put(user2, new Object());
        for (Map.Entry<User, Object> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
