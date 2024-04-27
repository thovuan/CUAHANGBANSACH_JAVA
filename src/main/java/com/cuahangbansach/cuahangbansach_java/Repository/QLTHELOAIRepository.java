package com.cuahangbansach.cuahangbansach_java.Repository;


import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QLTHELOAIRepository extends JpaRepository<THELOAISACH, String> {
    @Query(value="select * from THELOAISACH", nativeQuery = true)
    public List<THELOAISACH> GetList();

    @Query(value="select * from THELOAISACH where matheloai = :id", nativeQuery = true)
    public THELOAISACH GetCategoryById(String id);

}
