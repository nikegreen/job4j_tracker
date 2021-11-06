package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .sorted((l, r) -> l.getCity().compareTo(r.getCity()))
                .distinct()
                .collect(Collectors.toList());
    }
}
