package ru.job4j.collection.map;

import java.util.*;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("John", 2, new GregorianCalendar(1973, Calendar.MAY, 28));
        User user2 = new User("John", 2, new GregorianCalendar(1973, Calendar.MAY, 28));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (Map.Entry<User, Object> m : map.entrySet()) {
            System.out.println("key= " + m.getKey() + "   value= " + m.getValue());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday.getTimeInMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }
}
