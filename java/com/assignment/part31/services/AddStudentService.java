package com.assignment.part31.services;

import com.assignment.part31.data.JDBCManger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
@Profile("database")
public class AddStudentService {
    private final JDBCManger jdbcManger=new JDBCManger();
    public void insertIntoLogin(String userName, String password) throws SQLException {
        String query = "INSERT INTO login (userName,password,role) VALUES(? , ? , ?)";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setString(3, "student");
        statement.executeUpdate();
    }

    public void insertIntoStudent(String name, int loginID) throws SQLException {
        String query = "INSERT INTO user (name,loginID) VALUES(? , ?)";
        jdbcManger.insertINTO(query,name,loginID);
    }

    public Integer getID(String username, String password) throws SQLException {
        String query = "SELECT id FROM login WHERE username = ? AND password = ?";
        ResultSet resultSet = logInResult(username, password, query);
        resultSet.next();
        return resultSet.getInt("id");
    }

    public ResultSet logInResult(String username, String password, String query) throws SQLException {
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        return statement.executeQuery();
    }
}
