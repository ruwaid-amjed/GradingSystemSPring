package com.assignment.part31.controller;

import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.model.Class;
import com.assignment.part31.services.ShowAllClasses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ShowAllClassesController {
    private JDBCManger jdbcManger=new JDBCManger();
    private ShowAllClasses showAllClasses=new ShowAllClasses();

    @GetMapping("/showAllClasses")
    public String showAllClasses (Model model) throws SQLException, ClassNotFoundException {
        jdbcManger.loadDrive();

        List<Class> classes=showAllClasses.showClasses();
        model.addAttribute("courseInfoList",classes);

        return "show_all_classes";
    }
}
