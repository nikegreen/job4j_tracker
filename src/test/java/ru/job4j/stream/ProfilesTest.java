package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.stream.Profiles.collect;

public class ProfilesTest {
    @Test
    public void testAddress() {
        List<Address> addresses = Arrays.asList(
                new Address("Kirov", "Lenin", 5, 8),
                new Address("Moscow", "Lenin", 2, 11),
                new Address("Omsk", "Sadovaja", 1, 1)
                );
        List<Profile> profiles = Arrays.asList(
                new Profile(new Address("Moscow", "Lenin", 2, 11)),
                new Profile(new Address("Moscow", "Lenin", 2, 11)),
                new Profile(new Address("Kirov", "Lenin", 5, 8)),
                new Profile(new Address("Omsk", "Sadovaja", 1, 1))
        );
        List<Address> rsl = Profiles.collect(profiles);
        assertThat(rsl, is(addresses));
    }
}