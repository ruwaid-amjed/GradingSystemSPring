package com.assignment.part31.controller;

import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.services.AddStudentService;
import com.assignment.part31.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
@Controller
public class AddStudentController {
    private final AddStudentService service=new AddStudentService();
    private final LoginService loginService= new LoginService();
    private final JDBCManger jdbcManger=new JDBCManger();

    @GetMapping("/addStudent")
    public String getAddStudent(){
        return "add_student";
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam String username,@RequestParam String password,@RequestParam String studentName) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        jdbcManger.loadDrive();

        service.insertIntoLogin(username, loginService.hashPassword(password));

        int loginID= service.getID(username,loginService.hashPassword(password));
        service.insertIntoStudent(studentName,loginID);

        return "admin";
    }
}
