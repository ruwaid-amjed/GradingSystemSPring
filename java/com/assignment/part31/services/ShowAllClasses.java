package com.assignment.part31.services;

import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.model.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowAllClasses {
    private JDBCManger jdbcManger=new JDBCManger();
    public List<Class> showClasses() throws SQLException {
        String sqlQuery = "SELECT courseName, instructor.name, GROUP_CONCAT(user.name) AS studentNames " +
                "FROM course, instructor, user, student_course " +
                "WHERE instructor.instructorID = course.instructorID " +
                "AND student_course.stID = user.userID " +
                "AND student_course.courseID = course.courseID " +
                "GROUP BY courseName, instructor.name";

        Statement statement=jdbcManger.connect().createStatement();
        ResultSet resultSet=statement.executeQuery(sqlQuery);
        List<Class> classes=new ArrayList<>();

        while (resultSet.next()){
            String course= resultSet.getString("courseName");
            String instructor=resultSet.getString("name");
            String students= resultSet.getString("studentNames");

            Class c1 =new Class(course,instructor,students);
            classes.add(c1);
        }
        return classes;
    }
}
