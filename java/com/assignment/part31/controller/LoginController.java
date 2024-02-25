package com.assignment.part31.controller;


import com.assignment.part31.data.JDBCManger;
import com.assignment.part31.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Controller
@RequestMapping("/gradingSystem")
public class LoginController {
    private final JDBCManger jdbcManger=new JDBCManger();
    private final LoginService service=new LoginService();
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
    @PostMapping("/login")
    public String handleLogin(@RequestParam String name, @RequestParam String password, Model model, HttpSession session) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        jdbcManger.loadDrive();
        if(name.equalsIgnoreCase("admin1")){
            boolean isAuthenticated= service.authenticateUser(name, password);
            if(isAuthenticated)
                return "admin";
            else{
                model.addAttribute("errorMessage","Incorrect username or password!!");
                return "login";
            }
        }
        else {
            boolean isAuthenticated= service.authenticateUser(name,service.hashPassword(password));

            if (isAuthenticated){
                String role=service.getRole(name,service.hashPassword(password));
                if(role.equalsIgnoreCase("instructor")){
                    int loginID= service.getID(name,service.hashPassword(password));
                    int id= service.getInstructorID(loginID);
                    session.setAttribute("instructorID",id);


                    return "instructor";

                }
                else if (role.equalsIgnoreCase("student")){
                    int loginID= service.getID(name,service.hashPassword(password));
                    int id= service.getUserID(loginID);
                    session.setAttribute("stID",id);

                    return "student";

                }
            }
            else{
                model.addAttribute("errorMessage","Incorrect username or password!!");
                return "login";
            }
        }
        model.addAttribute("errorMessage","Incorrect username or password!!");
        return "login";
    }
}
