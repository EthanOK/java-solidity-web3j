package org.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.cdimascio.dotenv.Dotenv;

public class ConncetDB {
    static Dotenv dotenv = Dotenv.load();
    static String RPC = dotenv.get("ALCHEMY_GOERLI_URL");
    static String jdbcUrl = dotenv.get("MYSQL_JDBC_URL");
    static String username = dotenv.get("MYSQL_USERNAME");
    static String password = dotenv.get("MYSQL_PASSWORD");

    public static Connection getConnect() {

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            return connection;

            // Statement statement = connection.createStatement();

            // statement.close();
            // connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Statement getStatement() {

        try {
            Connection connection = getConnect();
            return connection.createStatement();

            // statement.close();
            // connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
