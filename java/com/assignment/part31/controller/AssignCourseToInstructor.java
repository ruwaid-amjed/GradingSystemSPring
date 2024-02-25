package com.assignment.part31.controller;

import com.assignment.part31.data.CourseDAO;
import com.assignment.part31.data.JDBCManger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class AssignCourseToInstructor {
    private final JDBCManger jdbcManger=new JDBCManger();

    @PostMapping("/assignCourseToInstructor")
    public String assignCourseToInstructor(@RequestParam String course,@RequestParam String instructorID) throws ClassNotFoundException, SQLException {
        jdbcManger.loadDrive();
        CourseDAO courseDAO=new CourseDAO();
        int id =Integer.parseInt(instructorID);
        int courseID =Integer.parseInt(course);

        courseDAO.assignCourseToInstructor(courseID,id);

        return "admin";
    }

}
