package com.assignment.part31.controller;

import com.assignment.part31.model.ShowGrades;
import com.assignment.part31.services.GradeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ShowGradeController {

    @GetMapping("/showGrades")
    public String showGrades(HttpSession session, Model model) throws SQLException {
        int stID= (int) session.getAttribute("stID");
        GradeService service=new GradeService();
        List<ShowGrades> gradesList=service.showGrades(stID);
        model.addAttribute("grades",gradesList);

        double gpa= service.getGPA(stID);
        model.addAttribute("gpa",gpa);

        return "show_grades";
    }

}
