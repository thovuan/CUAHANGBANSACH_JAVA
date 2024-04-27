package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QLNXBRepository extends JpaRepository<NXB, String> {
    @Query(value="select * from NXB where manxb = :id", nativeQuery = true)
    public NXB GetNXBById(String id);
}
