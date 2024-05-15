package com.cuahangbansach.cuahangbansach_java.RestAPI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChartDataController {
    @GetMapping("/CD/chartData")
    public String getChartData(Model model) {
        Map<String, Object> data = new HashMap<>();
        data.put("columns", new String[]{"Year", "Sales", "Expenses"});
        data.put("rows", new Object[][]{
                {"2014", 1000, 400},
                {"2015", 1170, 460},
                {"2016", 660, 1120},
                {"2017", 1030, 540}
        });
        //return data;
        model.addAttribute("chartData", data);
        return "/CD/chartData";
    }
}
