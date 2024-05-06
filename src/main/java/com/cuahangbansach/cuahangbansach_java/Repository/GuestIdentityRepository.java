package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestIdentityRepository extends JpaRepository<KHACH, String> {
    @Query(value = "select * from KHACH where tendangnhap = :username", nativeQuery = true)
    public KHACH GetByUsername(String username);
}
