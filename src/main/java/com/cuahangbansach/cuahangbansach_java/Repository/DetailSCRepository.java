package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.BookSales;
import com.cuahangbansach.cuahangbansach_java.Model.CHITIETDATHANG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailSCRepository extends JpaRepository<CHITIETDATHANG, String> {
    @Query(value = "select * from CHITIETDATHANG where maphieumuahang = :DHId and masach = :HonId", nativeQuery = true)
    public CHITIETDATHANG GetId(String DHId, String HonId);

    @Query(value = "select * from CHITIETDATHANG where maphieumuahang = :DHId", nativeQuery = true)
    public CHITIETDATHANG GetDHId(String DHId);

    @Query(value = "select * from CHITIETDATHANG where maphieumuahang = :DHId", nativeQuery = true)
    public List<CHITIETDATHANG> GetListDHId(String DHId);

//    @Query("SELECT BookSales(o.masach, SUM(o.quantity)) " +
//            "FROM CHITIETDATHANG o GROUP BY o.bookId ORDER BY SUM(o.quantity) DESC")
//    List<BookSales> findTopSellingBooks();



}
