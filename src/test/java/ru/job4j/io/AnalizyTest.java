package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavaible1() throws IOException {
        File source = folder.newFile("source1.txt");
        File target = folder.newFile("target1.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String answer = "10:57:01;10:59:01" + System.lineSeparator()
                      + "11:01:02;11:02:02" + System.lineSeparator();
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(v -> rsl.append(v + System.lineSeparator()));
        }
        assertThat(rsl.toString(), is(answer));
    }

    @Test
    public void unavaible2() throws IOException {
        File source = folder.newFile("source1.txt");
        File target = folder.newFile("target1.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String answer = "10:57:01;11:02:02" + System.lineSeparator();
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(v -> rsl.append(v + System.lineSeparator()));
        }
        assertThat(rsl.toString(), is(answer));
    }
}