
package org.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectionDemo {
    public static void main(String[] args) {

        try {
            Connection connection = ConncetDB.getConnect();
            System.out.println("Connected to the database!");

            Statement statement = connection.createStatement();

            // select
            String query = "SELECT id, `key`, value FROM aggregator_ethan.`system`;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String key = resultSet.getString("key");
                // 获取其他列的数据
                System.out.println("ID: " + id + ", key: " + key);
            }

            // insert
            String insertQuery = "INSERT INTO aggregator_ethan.`system` (`key`, value) VALUES(?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "email");
            preparedStatement.setString(2, "john@example.com");

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully!");

            resultSet.close();
            preparedStatement.close();

            // statement.close();
            // connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}