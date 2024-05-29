package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Repository.RevenueStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueStatisticsService {

    @Autowired
    private RevenueStatisticsRepository revenueStatisticsRepository;

    public List<PHIEUMUAHANG> getRevenueStatisticsByAll() {
        return revenueStatisticsRepository.getRevenueStatisticsByAll();
    }

    public List<PHIEUMUAHANG> getRevenueStatisticsByYear(int year) {
        return revenueStatisticsRepository.getRevenueStatisticsByYear(year);
    }

    public List<PHIEUMUAHANG> getRevenueStatisticsByMonth(int year, int MB, int ME) {
        return revenueStatisticsRepository.getRevenueStatisticsByMonth(year,MB, ME);
    }


    public List<PHIEUMUAHANG> getRevenueStatisticsBDay(int DB, int DE) {
        return revenueStatisticsRepository.getRevenueStatisticsByDay(DB,DE);
    }
}
