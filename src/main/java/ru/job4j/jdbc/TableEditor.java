package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private final Properties properties;
    private Connection connection;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    static Properties loadConfig(String file) {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) throws Exception {
        TableEditor editor = new TableEditor(TableEditor.loadConfig("app.properties"));
        String tableName = "sample";
        editor.createTable(tableName);
        editor.addColumn(tableName, "first", "int");
        editor.renameColumn(tableName, "first", "second");
        editor.dropColumn(tableName, "second");
        editor.dropTable(tableName);
    }

    private void initConnection() {
        String url = properties.getProperty("hibernate.connection.url"); //"jdbc:postgresql://localhost:5432/idea_db";
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");

        try {
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            this.connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
            connection = null;
        }
    }

    private void executeAndPrint(String sql, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }

    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s ()",
                tableName
        );
        executeAndPrint(sql, tableName);
    }

    public void dropTable(String tableName)  {
        String sql = String.format(
                "drop table %s",
                tableName
        );
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception exception) {
            System.out.println("drop table failed");
            exception.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add column %s %s",
                tableName,
                columnName,
                type
        );
        executeAndPrint(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s",
                tableName,
                columnName
        );
        try {
            executeAndPrint(sql, tableName);
        } catch (Exception e) {
            System.out.println("Error in deleting column");
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName
        );
        executeAndPrint(sql, tableName);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}