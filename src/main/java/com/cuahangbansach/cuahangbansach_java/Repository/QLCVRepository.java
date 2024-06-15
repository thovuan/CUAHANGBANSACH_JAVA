package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.CHUCVU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QLCVRepository extends JpaRepository<CHUCVU, String> {
}
