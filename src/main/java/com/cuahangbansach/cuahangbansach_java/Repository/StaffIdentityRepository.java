package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffIdentityRepository extends JpaRepository<NHANVIEN, String> {
    @Query(value = "select * from NHANVIEN where tendangnhap = :username", nativeQuery = true)
    public NHANVIEN findByUserName(String username);
}
