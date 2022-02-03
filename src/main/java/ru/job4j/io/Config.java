package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private void validateAdd(String val) {
        val = val.trim();
        if (val.length() > 0) {
            if (val.charAt(0) != '#') {
                int index = val.indexOf('=');
                if (index < 1) {
                    throw new IllegalArgumentException();
                }
                String key = val.substring(0, index);
                String param = val.substring(index + 1);
                values.put(key, param.length() > 0 ? param : "");
            }
        }
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values.clear();
            read.lines().forEach(this::validateAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return Optional.ofNullable(values.get(key)).orElseThrow(NullPointerException::new);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}

