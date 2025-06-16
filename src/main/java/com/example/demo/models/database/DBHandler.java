package com.example.demo.models.database;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

        //Строка подключения
        String connectionString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

        //Установка драйвера
//        Class.forName("com.mysql.jdbc.Driver");

        //Заключение подключения в переменную
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void regUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " ("
                + Const.USERS_LOGIN + ", " + Const.USERS_PASSWORD + ", "
                + Const.USERS_NAME + ")"
                + "VALUES (?, ?, ?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert);
            prst.setString(1, user.getLogin());
            prst.setString(2, user.getPassword());
            prst.setString(3, user.getUsername());

            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, user.getUsername());
            prst.setString(2, user.getPassword());

            resSet = prst.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public List<Test> loadTestsFromDB() {
        List<Test> list = new ArrayList<>();

        String querry = "SELECT \"testText\", \"correctAnswer\", \"wrongFirst\", \"wrongSecond\" FROM tests";
        try(Connection conn = getDbConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(querry);
            while(rs.next()) {
                String testText = rs.getString(Const.TEST_TEXT);
                String correct = rs.getString(Const.TEST_CORRECT_ANSWER);
                String wrong1 = rs.getString(Const.WRONG_FIRST);
                String wrong2 = rs.getString(Const.WRONG_SECOND);

                list.add(new Test(testText, correct, wrong1, wrong2));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

}
