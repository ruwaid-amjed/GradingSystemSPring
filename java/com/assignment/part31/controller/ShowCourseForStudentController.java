package com.assignment.part31.controller;

import com.assignment.part31.data.CourseDAO;
import com.assignment.part31.model.Course;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ShowCourseForStudentController {

    @GetMapping("/showCourseForStudent")
    public String showCourseForStudent(HttpSession session, Model model) throws SQLException {
        int stID= (int) session.getAttribute("stID");
        CourseDAO courseDAO=new CourseDAO();
        List<Course> courseList=courseDAO.showCourses(stID);
        model.addAttribute("courseList",courseList);

        return "student_show_courses";
    }
}
