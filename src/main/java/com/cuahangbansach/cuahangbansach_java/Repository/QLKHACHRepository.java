package com.cuahangbansach.cuahangbansach_java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;

import java.util.List;

@Repository
public interface QLKHACHRepository extends JpaRepository<KHACH, String> {
    @Query(value = "select * from KHACH",  nativeQuery=true)
    public List<KHACH> GetList();

    @Query(value = "select * from KHACH where makhachhang = :id", nativeQuery=true)
    public KHACH GetById(String id);

    @Query(value = "select * from KHACH where tenkhachhang like %:name%", nativeQuery=true)
    public List<KHACH> GetByName(String name);

}
