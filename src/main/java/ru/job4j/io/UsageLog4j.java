package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean human = true;
        byte fingers = 20;
        float weight = (float) 85.55;
        double height = 181.1;
        char sex = 'M';
        long secondsLive = 12849567;
        short size = 50;
        LOG.debug("User info name : {}, age : {} {} {} {} {} {} {} {} ", name, age, human, fingers, weight, height, sex, secondsLive, size);
    }
}