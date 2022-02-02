package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("nikegreen"));
        assertNull(config.value("surname"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("nikegreen"));
        assertNull(config.value("surname"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithIllegalArgumentException() {
        String path = "./data/pair_with_illegal_argument.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("nikegreen"));
        assertNull(config.value("surname"));
    }
}
