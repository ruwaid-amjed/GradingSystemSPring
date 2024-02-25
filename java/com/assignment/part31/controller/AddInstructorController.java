package com.assignment.part31.controller;

import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.services.AddInstructorService;
import com.assignment.part31.services.AddStudentService;
import com.assignment.part31.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Controller
public class AddInstructorController {
    private final AddStudentService service=new AddStudentService();
    private final LoginService loginService= new LoginService();
    private final JDBCManger jdbcManger=new JDBCManger();
    private final AddInstructorService instructorService=new AddInstructorService();

    @GetMapping("/addInstructor")
    public String getAddInstructor(){
        return "add_instructor";
    }

    @PostMapping("/addInstructor")
    public String addInstructor(@RequestParam String username, @RequestParam String password, @RequestParam String instructorName) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        jdbcManger.loadDrive();

        instructorService.insertIntoLogin(username, loginService.hashPassword(password));

        int loginID= service.getID(username,loginService.hashPassword(password));
        instructorService.insertIntoInstructor(instructorName,loginID);

        return "admin";
    }
}
