package com.assignment.part31.services;

import com.assignment.part31.data.JDBCManger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
@Repository
@Profile("database")
public class AddCourseService {
    private final JDBCManger jdbcManger=new JDBCManger();
    public void insertIntoCourse(String courseName) throws SQLException {
        String query = "INSERT INTO course (courseName) VALUES(?)";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setString(1, courseName);
        statement.executeUpdate();
    }
}
