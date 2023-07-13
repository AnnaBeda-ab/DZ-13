package utils;

import person.Man;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    //що підключитися до БД потрібні URL, username, password
    private final static String URL = "jdbc:postgresql://localhost:4567/postgres";

    // шаблон, де postgres - назва бази до якої конектимся
    private final static String USER_NAME = "AnnaBeda";
    private final static String PASSWORD = "qwert123";
    private final static String QUERY = "Select * from Man";
    private final static String QUERYY = "Select * from Man where id=?";
    private final static String QUERYINS = "Insert into man (firstname, lastname, age, partner) values (?,?,?,?)";
    private final static String QUERYUPD = "Update man set firstname=? where id=?";
    private final static String QUERYDEL = "Delete from man where id=?";


    public static List<Man> getManFromDB() {
        List<Man> manList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            Statement sqlstatement = connection.createStatement();
            ResultSet resultSet = sqlstatement.executeQuery(QUERY);  // робота з цим класом буде вертати результати
            while (resultSet.next()) {
                Man man = new Man(resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getBoolean("partner"));
                manList.add(man);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(String.format("Please check connection  string " + "URL [%s]" + "USER_NAME [%s]" +
                    "PASSWORD [%s]", URL, USER_NAME, PASSWORD));
        }
        return manList;
    }

    public static List<Man> getManFromDBSecure(int id) {
        List<Man> manList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERYY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Man man = new Man(resultSet.getString("firstnane"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getBoolean("partner"));
                manList.add(man);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(String.format("Please check connection  string " + "URL [%s]" + "USER_NAME [%s]" +
                    "PASSWORD [%s]", URL, USER_NAME, PASSWORD));
        }
        return manList;
    }

    public static void insertManToDBSecure(String firstName, String lastName, int age, boolean partner) {

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERYINS);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setBoolean(4, partner);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(String.format("Please check connection  string " + "URL [%s]" + "USER_NAME [%s]" +
                    "PASSWORD [%s]", URL, USER_NAME, PASSWORD));
        }
    }

    public static void updateManToDBSecure(String firstName, int id) {

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERYUPD);

            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(String.format("Please check connection  string " + "URL [%s]" + "USER_NAME [%s]" +
                    "PASSWORD [%s]", URL, USER_NAME, PASSWORD));
        }
    }
    public static void deleteManToDBSecure(int id) {

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERYDEL);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(String.format("Please check connection  string " + "URL [%s]" + "USER_NAME [%s]" +
                    "PASSWORD [%s]", URL, USER_NAME, PASSWORD));
        }
    }

    public static void main(String[] args) {
        List<Man> lsStart = getManFromDB();
        System.out.println();
        insertManToDBSecure("Boris","Gates",70,true);
        updateManToDBSecure("Alex-New",1);
        deleteManToDBSecure(2);


        List<Man> lsEnd = getManFromDBSecure(2);
        System.out.println();

    }
}
