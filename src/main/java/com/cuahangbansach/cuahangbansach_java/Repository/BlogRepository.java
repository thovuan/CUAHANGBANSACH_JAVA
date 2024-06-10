package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    @Query(value = "select * from Blog where year(ngayviet) = :years", nativeQuery = true)
    public List<Blog> BlogRevenueByYear(int years);

    @Query(value = "select * from Blog where year(ngayviet) = :years and month(ngayviet) = :months", nativeQuery = true)
    public List<Blog> BlogRevenueByYear(int years, int months);

    @Query(value = "select * from Blog where year(ngayviet) = :year and month(ngayviet) between :monthBG and :monthE", nativeQuery = true)
    public List<Blog> BlogRevenueByYearAndMonth(int year, int monthBG, int monthE);
}
