package com.assignment.part31.controller;

import com.assignment.part31.data.CourseDAO;
import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.data.StudentDAO;
import com.assignment.part31.model.Course;
import com.assignment.part31.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class SelectStudentController {
    private final JDBCManger jdbcManger=new JDBCManger();

    @GetMapping("/selectStudent")
    public String getSelectStudent(Model model) throws ClassNotFoundException, SQLException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAllStudent();
        model.addAttribute("students",students);

        return "select_student";
    }

    @PostMapping("/selectStudent")
    public String selectStudent(@RequestParam String student,Model model) throws SQLException {
        int stID=Integer.parseInt(student);
        CourseDAO courseDAO=new CourseDAO();

        List<Course> registeredCourses=courseDAO.showCoursesForStudent(stID);
        model.addAttribute("registeredCourses",registeredCourses);

        List<Course> availableCourses=courseDAO.showCoursesAvailableForStudent(stID);
        model.addAttribute("availableCourses",availableCourses);
        model.addAttribute("stID",stID);

        return"assign_course_to_student";
    }
}
