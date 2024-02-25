package com.assignment.part31.services;

import com.assignment.part31.data.JDBCManger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@Profile("database")
public class AddInstructorService {
    private final JDBCManger jdbcManger=new JDBCManger();
    public void insertIntoLogin(String userName, String password) throws SQLException {
        String query = "INSERT INTO login (userName,password,role) VALUES(? , ? , ?)";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setString(3, "instructor");
        statement.executeUpdate();
    }

    public void insertIntoInstructor(String name, int loginID) throws SQLException {
        String query = "INSERT INTO instructor (name,loginID) VALUES(? , ?)";
        jdbcManger.insertINTO(query,name,loginID);
    }
}
