package com.example.demo.models.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class DBHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

        //Строка подключения
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

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
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_NAME + "=? AND " + Const.USERS_PASSWORD + "=?";

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

}
