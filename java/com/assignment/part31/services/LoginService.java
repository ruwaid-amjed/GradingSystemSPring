package com.assignment.part31.services;

import com.assignment.part31.data.JDBCManger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Profile("database")
public class LoginService {
    private final JDBCManger jdbcManger=new JDBCManger();
    public  String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public boolean authenticateUser(String username, String password) throws SQLException {
        String query = "select * from login where userName=? and password=?";
        try (PreparedStatement statement = jdbcManger.connect().prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public String getRole(String username, String password) throws SQLException {
        String query = "SELECT role FROM login WHERE username = ? AND password = ?";
        ResultSet resultSet = jdbcManger.logInResult(username, password, query);
        resultSet.next();
        return resultSet.getString("role");
    }

    public Integer getID(String username, String password) throws SQLException {
        String query = "SELECT id FROM login WHERE username = ? AND password = ?";
        ResultSet resultSet = jdbcManger.logInResult(username, password, query);
        resultSet.next();
        return resultSet.getInt("id");
    }

    public Integer getUserID(int loginID) throws SQLException {
        String query = "SELECT userID FROM user WHERE loginID = ?";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, loginID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("userID");
    }
    public Integer getInstructorID(int loginID) throws SQLException {
        String query = "SELECT instructorID FROM instructor WHERE loginID = ?";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, loginID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("instructorID");

    }
}
