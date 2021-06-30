package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> dialog = new LinkedList<>();
    private final List<String> answers = new ArrayList<>();
    Scanner console = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        try (BufferedReader read = new BufferedReader(new FileReader(botAnswers))) {
            read.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\chat.log", ".\\bot.answers");
        cc.run();
    }

    public void run() {
        String userString;
        boolean answerPermission = true;
        Random random = new Random();
//
        do {
            userString = console.nextLine();
            dialog.add(userString);
            if (userString.equals(STOP) || userString.equals(OUT)) answerPermission = false;
            if (userString.equals(CONTINUE)) answerPermission = true;

            if (answerPermission) {
                String answer = answers.get(random.nextInt(answers.size()));
                dialog.add(answer);
                System.out.println(answer);
            }

        } while (!userString.equals(OUT));

// запись диалога по окончании чата
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)
                ))) {
            dialog.forEach(out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}