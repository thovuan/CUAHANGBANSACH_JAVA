package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.KHUYENMAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<KHUYENMAI, String> {

    @Query(value = "select * from KHUYENMAI where conditioncost <= :Cost", nativeQuery = true)
    public List<KHUYENMAI> GetSuggestPromotion(long Cost);
}
