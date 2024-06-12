package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.*;
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

    @Autowired
    private RevenueService revenueService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private ShoppingCartService shoppingCartService;

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

        if (MB == null && ME == null) {
            model.addAttribute("chartData", revenueStatisticsService.getChart());
        }
        else if (MB != null && ME != null) {
            model.addAttribute("chartData", revenueStatisticsService.getRevenueStatisticsByMonth(2024, Integer.parseInt(MB), Integer.parseInt(ME)));

        }
        //model.addAttribute("chartData", revenueStatisticsService.getChart());
        return "/QLDONHANG/RevenueStatistics/Index";
    }

    @GetMapping("/QLDONHANG/RevenueStatistics/Index2")
    public String Index2(Model model) {
        model.addAttribute("chartData", revenueStatisticsService.getRevenueStatisticsByYearandMonthandDay(2024,5));
        return "/QLDONHANG/RevenueStatistics/Index2";
    }
    @GetMapping("/Revenue/All")
    public String TotalRevenue  (Model model) {
        model.addAttribute("TotalRevenue", revenueService.TotalRevenue());
        model.addAttribute("Cart", revenueService.CartSize());
        model.addAttribute("BookSeller", revenueService.BookSellerRe());
        model.addAttribute("User", revenueService.UserNumber());
        model.addAttribute("Blog", revenueService.BlogActivity());

        model.addAttribute("chartData", revenueStatisticsService.getChart());
        model.addAttribute("chartData2", qlsachService.GetBookSellerRevenue(2024));
        model.addAttribute("chartData3", shoppingCartService.CartRevenue());
        model.addAttribute("chartData4", blogService.getBlogRevenue(2024));
        return "/Revenue/TotalRevenue";
    }

}
