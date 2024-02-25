package com.assignment.part31.controller;

import com.assignment.part31.data.CourseDAO;
import com.assignment.part31.data.InstructorDAO;
import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.data.StudentDAO;
import com.assignment.part31.model.Course;
import com.assignment.part31.model.Instructor;
import com.assignment.part31.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
@Controller
public class SelectInstructorController {
    private final JDBCManger jdbcManger=new JDBCManger();

    @GetMapping("/selectInstructor")
    public String getSelectStudent(Model model) throws ClassNotFoundException, SQLException {
        InstructorDAO instructorDAO =new InstructorDAO();
        List<Instructor> instructorList=instructorDAO.showAllInstructor();

        model.addAttribute("instructorList",instructorList);
        return "select_Instructor";
    }

    @PostMapping("/selectInstructor")
    public String selectStudent(@RequestParam String instructor, Model model) throws SQLException {
        int id=Integer.parseInt(instructor);
        CourseDAO courseDAO=new CourseDAO();

        List<Course> registeredCourses=courseDAO.showCoursesForInstructor(id);
        model.addAttribute("registeredCourses",registeredCourses);

        List<Course> availableCourses=courseDAO.showCoursesAvailableForInstructor();
        model.addAttribute("availableCourses",availableCourses);
        model.addAttribute("instructorID",id);

        return"assign_course_to_instructor";
    }
}

