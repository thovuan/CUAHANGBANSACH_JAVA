package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QLNVRepository extends JpaRepository<NHANVIEN, String> {
    @Query (value = "select * from NHANVIEN where manhanvien = :id", nativeQuery=true)
    public NHANVIEN GetById(String id);

    @Query (value = "select * from NHANVIEN where tennhanvien like %:staffname%", nativeQuery=true)
    public List<NHANVIEN> getName(String staffname);
}
