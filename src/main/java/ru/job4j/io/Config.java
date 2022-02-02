package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values.clear();
            read.lines().forEach(val -> {
                if (val.length() > 0) {
                    boolean rem = (val.length() >= 1 && val.charAt(0) == '#');
                    if (!rem) {
                        int index = val.indexOf('=');
                        if (index < 1) {
                            throw new IllegalArgumentException();
                        }
                        String key = val.substring(0, index);
                        String str = val.substring(index + 1, val.length());
                        values.put(key, str.length() > 0 ? str : null);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
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

