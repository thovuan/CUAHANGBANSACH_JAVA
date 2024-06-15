package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.CHITIETCHUCVU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CTCVRepository extends JpaRepository<CHITIETCHUCVU, String> {

    @Query(value = "select * from CHITIETCHUCVU where manhanvien = :manv and machucvu = :chitietchucvu", nativeQuery = true)
    public CHITIETCHUCVU findByChitietchucvu(String manv,String chitietchucvu);
}
