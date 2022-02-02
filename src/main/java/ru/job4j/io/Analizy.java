package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        class InputLog {
            private final boolean available;
            private final String time;

            public InputLog(String str) {
                String[] s = str.split(" ");
                int res = switch (Integer.valueOf(s[0])) {
                    case 200 -> 1;
                    case 300 -> 1;
                    case 400 -> -1;
                    case 500 -> -1;
                    default -> 0;
                };
                available = res == 1;
                time = s[1];
            }

            public boolean isAvailable() {
                return available;
            }

            public String getTime() {
                return time;
            }
        }

        List<InputLog> in = new  ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(val -> {
                if (val.length() == 12) {
                   in.add(new InputLog(val));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            Iterator<InputLog> inIterator = in.iterator();
            InputLog inputLog;
            while (inIterator.hasNext()) {
                do {
                    inputLog = inIterator.next();
                } while (inIterator.hasNext() && inputLog.isAvailable());
                String begTime = inputLog.getTime();
                do {
                    inputLog = inIterator.next();
                } while (inIterator.hasNext() && !inputLog.isAvailable());
                String endTime = inputLog.getTime();
                out.println(begTime + ";" + endTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy a = new Analizy();
        a.unavailable("./data/log1.log", "unavailable1.csv");
        a.unavailable("./data/log2.log", "unavailable2.csv");
    }
}