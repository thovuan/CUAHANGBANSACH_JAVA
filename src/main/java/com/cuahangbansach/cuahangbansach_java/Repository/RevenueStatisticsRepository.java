package com.cuahangbansach.cuahangbansach_java.Repository;


import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RevenueStatisticsRepository extends JpaRepository<PHIEUMUAHANG, String> {

    @Query(value = "select * from PHIEUMUAHANG", nativeQuery = true)
    public List<PHIEUMUAHANG> getRevenueStatisticsByAll();

    @Query(value = "select * from PHIEUMUAHANG where year(ngaylap) = :years ", nativeQuery = true)
    public List<PHIEUMUAHANG> getRevenueStatisticsByYear(int years);

    @Query(value = "select * from PHIEUMUAHANG where year(ngaylap) = :years and month(ngaylap) = :months", nativeQuery = true)
    public List<PHIEUMUAHANG> getRevenueStatisticsByYearandMonth(int months, int years);

    @Query(value = "select * from PHIEUMUAHANG where year(ngaylap) = :years and month(ngaylap) between :MB and :ME", nativeQuery = true)
    public List<PHIEUMUAHANG> getRevenueStatisticsByMonth(int years, int MB, int ME);

    @Query(value = "select * from PHIEUMUAHANG where date(ngaylap) between :DB and :DE", nativeQuery = true)
    public List<PHIEUMUAHANG> getRevenueStatisticsByDay(int DB, int DE);

}
