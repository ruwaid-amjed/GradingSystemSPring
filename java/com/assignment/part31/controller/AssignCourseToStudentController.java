package com.assignment.part31.controller;

import com.assignment.part31.data.CourseDAO;
import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.data.StudentDAO;
import com.assignment.part31.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class AssignCourseToStudentController {
    private final JDBCManger jdbcManger=new JDBCManger();


    @PostMapping("/assignCourseToStudent")
    public String assignCourseToStudent(@RequestParam String course,@RequestParam String stID) throws ClassNotFoundException, SQLException {
        jdbcManger.loadDrive();
        CourseDAO courseDAO=new CourseDAO();
        int id =Integer.parseInt(stID);
        int courseID =Integer.parseInt(course);

        courseDAO.insertINTOStudentCourse(id,courseID);

        return "admin";

    }

}
