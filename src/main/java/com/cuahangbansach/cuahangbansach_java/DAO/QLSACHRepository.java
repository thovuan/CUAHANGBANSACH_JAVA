package com.cuahangbansach.cuahangbansach_java.DAO;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;

import java.util.List;

@Repository
public interface QLSACHRepository extends JpaRepository<SACH, Integer> {
    @Query(value="select * from SACH", nativeQuery = true)
    public List<SACH> GetList();
}
