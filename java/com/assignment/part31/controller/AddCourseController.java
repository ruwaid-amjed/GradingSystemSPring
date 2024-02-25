package com.assignment.part31.controller;

import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.services.AddCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class AddCourseController {

    private final JDBCManger jdbcManger=new JDBCManger();
    private final AddCourseService service=new AddCourseService();

    @GetMapping("/addCourse")
    public String getAddCourse(){
        return "add_course";
    }

    @PostMapping("/addCourse")
    public String addCourse(@RequestParam String courseName) throws SQLException, ClassNotFoundException {
        jdbcManger.loadDrive();

        service.insertIntoCourse(courseName);

        return "admin";

    }
}
