package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        System.out.println(config);
        assertThat(config.value("a"), is("b"));
        assertThat(config.value("surname"), is(nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithIllegal() {
        String path = "./data/pair_with_illegal.properties";
        Config config = new Config(path);
        config.load();
    }
}