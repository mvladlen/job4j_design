package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class Person {
    private final String name;
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;
    private final Purse purse;

    public Person(String name, boolean sex, int age, Contact contact, String... statuses) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
        this.purse = new Purse(name);
    }

    public static void main(String[] args) {
        final Person person = new Person("Petr", false, 30, new Contact(450000, "11-111"), "Worker", "Married");
        person.getMoney(asList(new BankNote(100, "RUB"), new BankNote(20, "USD")));
        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();


        /* формируем json-строку */
        final String personJson = gson.toJson(person);
        System.out.println(personJson);
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }

    public void getMoney(List<BankNote> money) {
        money.forEach(bankNote -> purse.addBanknote(bankNote.getNominal(), bankNote.getCurrency()));
    }

    @Override
    public String toString() {
        return "Person{"
                + "name=" + name
                + ", sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    private static class Purse {
        final String ownerName;
        final List<BankNote> bankNotes;

        private Purse(String ownerName) {
            this.ownerName = ownerName;
            this.bankNotes = new LinkedList<>();
        }

        public void addBanknote(int nom, String val) {
            bankNotes.add(new BankNote(nom, val));
        }
    }

    private static class BankNote {
        final int nominal;
        final String currency;

        private BankNote(int nominal, String currency) {
            this.nominal = nominal;
            this.currency = currency;
        }

        public int getNominal() {
            return nominal;
        }

        public String getCurrency() {
            return currency;
        }
    }
}