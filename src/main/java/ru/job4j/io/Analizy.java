package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            BufferedReader read = new BufferedReader(new FileReader(source));
            String val;
            boolean oldAvailable = true;
            String begTime = "";
            while ((val = read.readLine()) != null) {
                val = val.trim();
                int index = val.indexOf(' ');
                String err;
                String time;
                if (val.length() > 3
                        && index > 0
                        && index < val.length() - 1
                ) {
                    err = val.substring(0, index);
                    time = val.substring(index + 1);
                    boolean available = "200".equals(err) || "300".equals(err);
                    if (oldAvailable != available) {
                        if (available) {
                            out.println(begTime + ";" + time);
                        } else {
                            begTime = time;
                        }
                        oldAvailable = available;
                    }
                }
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