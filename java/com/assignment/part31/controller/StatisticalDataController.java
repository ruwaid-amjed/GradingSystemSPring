package com.assignment.part31.controller;

import com.assignment.part31.data.StatisticalDataDAO;
import com.assignment.part31.model.StatisticalData;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class StatisticalDataController {

    @GetMapping("/statisticalData")
    public String statisticalData(HttpSession session, Model model) throws SQLException {
        StatisticalDataDAO statisticalDataDAO=new StatisticalDataDAO();
        int instructorID= (int) session.getAttribute("instructorID");
        List<StatisticalData> dataList=statisticalDataDAO.statisticalDataList(instructorID);

        model.addAttribute("dataList",dataList);

        return "statistical_data";
    }
}
