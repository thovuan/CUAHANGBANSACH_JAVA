package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.RevenueStatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RevenueStatisticsController {

    @Autowired
    private RevenueStatisticsService revenueStatisticsService;

//    @GetMapping("/QLDONHANG/RevenueStatistics/Index")
//    public String index(Model model, @RequestParam(name = "year", required = false) String year,
//                        @RequestParam (name = "monthbegin", required = false)String MB, @RequestParam(name = "monthend", required = false)String ME) {
//
//        List<PHIEUMUAHANG> tk;
//        tk = revenueStatisticsService.getRevenueStatisticsByAll();
//        if (MB==null || ME == null) {
//                tk = revenueStatisticsService.getRevenueStatisticsByYear(Integer.parseInt(year));
//            }
//        else {
//            int MTB = Integer.parseInt(MB);
//            int MTME = Integer.parseInt(ME);
//
//            if ((MTB < 1 || MTB > 12) &&(MTME < 1 || MTME > 12)) {
//                model.addAttribute("errorMessage", "Invalid Month");
//                return "/QLDONHANG/RevenueStatistics/Index";
//            }
//
//            else tk = revenueStatisticsService.getRevenueStatisticsByMonth(Integer.parseInt(year), MTB, MTME);
//            //
//        }
//
//        long TH = 0;
//        for (PHIEUMUAHANG pmh : tk) {
//            TH += pmh.getDHTotal();
//        }
//
//        model.addAttribute("PMH", tk);
//        model.addAttribute("CostTotal", TH);
//        return "/QLDONHANG/RevenueStatistics/Index";
//    }

    @GetMapping("/QLDONHANG/RevenueStatistics/Index")
    public String Index(Model model, @RequestParam(name = "monthbegin", required = false)String MB, @RequestParam(name = "monthend", required = false)String ME) {
        //List<List<Object>> list = revenueStatisticsService.getChart();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String jsonData = mapper.writeValueAsString(list);
//            model.addAttribute("chartData", jsonData);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            model.addAttribute("errorMessage", "Error processing JSON");
//            return "/QLDONHANG/RevenueStatistics/Index";
//        }
        if (MB == null && ME == null) {
            model.addAttribute("chartData", revenueStatisticsService.getChart());
        }
        else if (MB != null && ME != null) {
            model.addAttribute("chartData", revenueStatisticsService.getRevenueStatisticsByMonth(2024, Integer.parseInt(MB), Integer.parseInt(ME)));

        }
        //model.addAttribute("chartData", revenueStatisticsService.getChart());
        return "/QLDONHANG/RevenueStatistics/Index";
    }


}
