package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Repository.RevenueStatisticsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RevenueStatisticsService {

    @Autowired
    private RevenueStatisticsRepository revenueStatisticsRepository;

    public List<List<Object>> getChart() {
        List<List<Object>> data = new ArrayList<>();
        //List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByYear(2024);
        long Totalcost = 0;
        for (int i =1 ; i<=12; i++) {
            List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByYearandMonth(i, 2024);
            List<Object> sub = new ArrayList<>();
            long DHTOTAL = 0;
            for (PHIEUMUAHANG dHbyYear : listDHbyYear) {
                DHTOTAL += dHbyYear.getDHTotal();
                Totalcost += DHTOTAL;
            }
            sub.add(i);
            sub.add(DHTOTAL);
            data.add(sub);
        }

        System.out.println(data);
        return data;
    }

//    public List<List<Object>> RevenueByDay(int year, int day) {
//        List<List<Object>> data = new ArrayList<>();
//        //List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByYear(2024);
//
//            List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByDay(year, day);
//            List<Object> sub = new ArrayList<>();
//            long DHTOTAL = 0;
//            for (PHIEUMUAHANG dHbyYear : listDHbyYear) {
//                DHTOTAL += dHbyYear.getDHTotal();
//
//            }
//            sub.add(day);
//            sub.add(DHTOTAL);
//            data.add(sub);
//
////        data.add(Arrays.asList(1,2024));
////        data.add(Arrays.asList(2,2026));
//        System.out.println(data);
//        return data;
//    }

    public List<PHIEUMUAHANG> getRevenueStatisticsByAll() {
        return revenueStatisticsRepository.getRevenueStatisticsByAll();
    }

    public List<PHIEUMUAHANG> getRevenueStatisticsByYear(int year) {
        return revenueStatisticsRepository.getRevenueStatisticsByYear(year);
    }

    public List<List<Object>> getRevenueStatisticsByMonth(int year, int MB, int ME) {
        List<List<Object>> data = new ArrayList<>();
        //List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByYear(2024);
        for (int i =MB ; i<=ME; i++) {
            List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByYearandMonth(i, 2024);
            List<Object> sub = new ArrayList<>();
            long DHTOTAL = 0;
            for (PHIEUMUAHANG dHbyYear : listDHbyYear) {
                DHTOTAL += dHbyYear.getDHTotal();

            }
            sub.add(i);
            sub.add(DHTOTAL);
            data.add(sub);
        }
//        data.add(Arrays.asList(1,2024));
//        data.add(Arrays.asList(2,2026));
        System.out.println(data);
        return data;
        //return revenueStatisticsRepository.getRevenueStatisticsByMonth(year,MB, ME);
    }


//    public List<PHIEUMUAHANG> getRevenueStatisticsBDay(int DB, int DE) {
//        return revenueStatisticsRepository.getRevenueStatisticsByDay(DB,DE);
//    }

    public int GetDayByMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year,month);
        return yearMonth.lengthOfMonth();
    }

    public List<List<Object>> getRevenueStatisticsByYearandMonthandDay(int year, int month) {
        List<List<Object>> data = new ArrayList<>();
        int daybymonth = GetDayByMonth(year, month);
        for (int i = 1; i <= daybymonth; i++) {
            List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByDay(year, month, i);
            List<Object> sub = new ArrayList<>();
            long DHTOTAL = 0;
            for (PHIEUMUAHANG dHbyYear : listDHbyYear) {
                DHTOTAL += dHbyYear.getDHTotal();


            }
            sub.add(i);
            sub.add(DHTOTAL);
            data.add(sub);
        }

        System.out.println(data);
        return data;
    }
}
