package com.assignment.part31.controller;

import com.assignment.part31.data.CourseDAO;
import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.model.Course;
import com.assignment.part31.model.Student;
import com.assignment.part31.services.GradeService;
import jakarta.servlet.http.HttpSession;
import jdk.jfr.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ShowCourseForInstructor {
    private JDBCManger jdbcManger =new JDBCManger();
    private CourseDAO courseDAO=new CourseDAO();

    @GetMapping("/showCourses")
    public String getShowCourses(HttpSession session, Model model) throws SQLException {
        int instructorID= (int) session.getAttribute("instructorID");
        List<Course> courseList=courseDAO.showCoursesForInstructor(instructorID);
        model.addAttribute("courseList",courseList);

        return "show_courses";
    }

    @GetMapping("/showCourses1")
    public String getShowCourses1(HttpSession session, Model model) throws SQLException {
        int instructorID= (int) session.getAttribute("instructorID");
        List<Course> courseList=courseDAO.showCoursesForInstructor(instructorID);
        model.addAttribute("courses",courseList);

        return "show_courses_for_instructor";
    }

    @PostMapping("/showCourses")
    public String showCourses(@RequestParam String course, HttpSession session,Model model) throws SQLException {
        GradeService service=new GradeService();
        int courseID=Integer.parseInt(course);
        session.setAttribute("courseID",courseID);

        List<Student> studentList=service.studentListWithGrades(courseID);
        model.addAttribute("studentListWithGrades",studentList);

        List<Student> studentList1=service.studentListWithoutGrades(courseID);
        model.addAttribute("studentListWithoutGrades",studentList1);

        return "input_grades";
    }
}
