package ru.job4j.serialization.json;

import com.sun.xml.txw2.annotation.XmlElement;
import ru.job4j.serialization.java.Contact;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;


@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean sex;
    private int age;
    private Contact contact;
    @XmlElementWrapper(name = "statuses")
    @javax.xml.bind.annotation.XmlElement (name = "status")
    private String[] statuses;
    private Purse purse;

    public Person() {
    }

    public Person(String name, boolean sex, int age, Contact contact, String... statuses) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
        this.purse = new Purse(name);
    }

    public static void main(String[] args) throws JAXBException {
        final Person person = new Person("Petr", false, 30, new Contact(450000, "11-111"), "Worker", "Married");
        person.getMoney(asList(new BankNote(100, "RUB"), new BankNote(20, "USD")));

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
        }

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
    @XmlElement
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Purse {
        final String ownerName;
        @XmlElementWrapper(name = "bankNotes")
        @javax.xml.bind.annotation.XmlElement (name = "banknote")
        List<BankNote> bankNotes;

        private Purse(String ownerName) {
            this.ownerName = ownerName;
            this.bankNotes = new LinkedList<>();
        }

        public void addBanknote(int nom, String val) {
            bankNotes.add(new BankNote(nom, val));
        }
    }

    @XmlElement("bankNote")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class BankNote {
        @XmlAttribute
        final int nominal;
        @XmlAttribute
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