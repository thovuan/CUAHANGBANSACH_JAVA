package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.THE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface CardRepository extends JpaRepository<THE, String> {
    @Query(value="select * from THE where makhachhang = :makhach", nativeQuery = true)
    public THE findByKHACH(String makhach);
}
