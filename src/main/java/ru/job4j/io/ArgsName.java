package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("No such argument");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args).forEach(x -> {
            String[] s = x.split("=");
            if ((s.length < 2)
                    || (s[0].charAt(0) != '-')
                    || (s[0].length() < 2)
                    || (s[1].length() < 1)) {
                throw new IllegalArgumentException("Arguments must have '-' at first position ");
            }
            values.put(s[0].substring(1), s[1]);
        });
    }

}