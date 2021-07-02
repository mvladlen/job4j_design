package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    Scanner console = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chat.log", "./bot.answers");
        cc.run();
    }

    private void readAnswers(List<String> answers, String infile) {
        try (BufferedReader read = new BufferedReader(new FileReader(infile))) {
            read.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String userString;
        boolean answerPermission = true;
        Random random = new Random();
        List<String> dialog = new LinkedList<>();
        List<String> answers = new ArrayList<>();

        readAnswers(answers, this.botAnswers);
        do {
            userString = console.nextLine();
            dialog.add(userString);
            if (userString.equals(STOP) || userString.equals(OUT)) {
                answerPermission = false;
            }
            if (userString.equals(CONTINUE)) {
                answerPermission = true;
            }

            if (answerPermission) {
                String answer = answers.get(random.nextInt(answers.size()));
                dialog.add(answer);
                System.out.println(answer);
            }
        } while (!userString.equals(OUT));
        writeLog(dialog, this.path);
    }

    private void writeLog(List<String> log, String path) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)
                ))) {
            log.forEach(out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

