package ru.job4j.io;

import java.io.*;

public class Analizy {

    public static void main(String[] args) {
    unavailable("server.log", "unavailable.log");

    }

    public static void unavailable(String source, String target) {
        LogEvent current = new LogEvent();
        LogEvent last = new LogEvent();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)))) {
                read.lines().forEach(x -> {
                    current.fromString(x);
                    if (current.bad() && last.good()) {
                        out.print(current + ";");
                    }
                    if (current.good() && last.bad()) {
                        if (last.errorCode != 0) {
                            out.println(current);
                        }
                    }
                    last.copy(current);
                });
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }


    private static class LogEvent {

        private int errorCode = 0;
        private String time;

        public void fromString(String string) {
            String[] wordList = string.split(" ");
            this.errorCode = Integer.parseInt(wordList[0]);
            this.time = wordList[1];
        }

        @Override
        public String toString() {
            return time;
        }

        public boolean bad() {
            return (errorCode == 400 || errorCode == 500);
        }

        public boolean good() {
            return !bad();
        }

        public void copy(LogEvent logEvent) {
            errorCode = logEvent.errorCode;
            time = logEvent.toString();
        }
    }
}