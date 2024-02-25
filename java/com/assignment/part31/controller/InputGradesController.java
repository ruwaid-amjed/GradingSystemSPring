package com.assignment.part31.controller;

import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.model.Student;
import com.assignment.part31.services.GradeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class InputGradesController {
    private JDBCManger jdbcManger =new JDBCManger();
    GradeService service=new GradeService();
    @PostMapping("/inputGrade")
    public String inputGrades(HttpSession session,@RequestParam("grades") List<String> grades) throws ClassNotFoundException, SQLException {
        jdbcManger.loadDrive();

        int courseID= (int) session.getAttribute("courseID");
        List<Student> studentList=service.studentListWithoutGrades(courseID);
        for (Student student:studentList){
            int stID=student.getId();
            service.insertIntoGrade(Integer.parseInt(grades.remove(0)),stID,courseID);
        }
        return "instructor";
    }
}
