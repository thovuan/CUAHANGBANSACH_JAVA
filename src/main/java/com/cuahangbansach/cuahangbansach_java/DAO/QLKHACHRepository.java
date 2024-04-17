package com.cuahangbansach.cuahangbansach_java.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;

import java.util.List;

@Repository
public interface QLKHACHRepository extends JpaRepository<KHACH, Integer> {
    @Query(value = "select * from KHACH",  nativeQuery=true)
    public List<KHACH> GetList();

}
