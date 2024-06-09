package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.RevenueStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rs")
public class RevenueStatisticAPIController {

    @Autowired
    private RevenueStatisticsService revenueStatisticsService;

    @GetMapping("/")
    public List<PHIEUMUAHANG> revenueStatistic(PHIEUMUAHANG phi) {
        return revenueStatisticsService.getRevenueStatisticsByAll();

    }


//    @GetMapping("/bymonth/{year}&{MB}&{ME}")
//    public List<PHIEUMUAHANG> revenueStatisticByMonth(@PathVariable("year") int year, @PathVariable("MB") int MB, @PathVariable("ME") int ME) {
//        return revenueStatisticsService.getRevenueStatisticsByMonth(year, MB, ME);
//    }
}
