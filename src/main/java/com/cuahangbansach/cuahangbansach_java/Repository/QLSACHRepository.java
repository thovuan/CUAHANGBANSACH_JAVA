package com.cuahangbansach.cuahangbansach_java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;

import java.util.List;

@Repository
public interface QLSACHRepository extends JpaRepository<SACH, String> {
    @Query(value="select * from SACH", nativeQuery = true)
    public List<SACH> GetList();

    @Query(value="select * from SACH where masach = :id", nativeQuery = true)
    public SACH GetSachById(String id);

    @Query(value="select * from SACH where matheloai = :MaTL", nativeQuery = true)
    public List<SACH> GetSachByMaTL(String MaTL);

    @Query(value="select * from SACH where manxb = :MaNXB", nativeQuery = true)
    public List<SACH> GetSachByMaNXB(String MaNXB);

    @Query(value = "select * from SACH where tensach like %:name%", nativeQuery = true)
    public List<SACH> GetSachbyName(String name);
}
