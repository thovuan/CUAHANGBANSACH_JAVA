package com.cuahangbansach.cuahangbansach_java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;

import java.util.List;

@Repository
public interface QLSACHRepository extends JpaRepository<SACH, String> {
    @Query(value="select * from SACH", nativeQuery = true)
    public List<SACH> GetList();

    @Query(value="select * from SACH where masach = :id", nativeQuery = true)
    public SACH GetSachById(String id);

    @Query(value="select * from SACH where matheloai = :MaTL", nativeQuery = true)
    public List<SACH> GetSachByMaTL(String MaTL);

    @Query(value="select * from SACH where manxb = :MaNXB", nativeQuery = true)
    public List<SACH> GetSachByMaNXB(String MaNXB);

    @Query(value = "select * from SACH where tensach like %:name%", nativeQuery = true)
    public List<SACH> GetSachbyName(String name);

    @Query(value = """
            select top(8) hon.masach, hon.tensach, hon.soluonghienco, hon.dacdiem, hon.anhsanpham, hon.DVT, hon.dongia,
            hon.matheloai, hon.manxb, hon.manhanvien, sum(ctdh.soluongmua) as total_quantity
            from SACH hon join CHITIETDATHANG ctdh on hon.masach = ctdh.masach
            group by hon.masach, hon.tensach, hon.soluonghienco, hon.dacdiem, hon.anhsanpham, hon.DVT, hon.dongia,
            hon.matheloai, hon.manxb, hon.manhanvien
            order by total_quantity desc""", nativeQuery = true)
    public List<SACH> Top8SellerBook();

//    @Query(value = """
//            select hon.masach, hon.tensach, hon.soluonghienco, hon.dacdiem, hon.anhsanpham, hon.DVT, hon.dongia,
//            hon.matheloai, hon.manxb, hon.manhanvien, sum(ctdh.soluongmua) as total_quantity
//            from SACH hon join CHITIETDATHANG ctdh on hon.masach = ctdh.masach
//            where year(ctdh.phieumuahang.ngaylap) = :years and month(ctdh.phieumuahang.ngaylap) = :months
//            group by hon.masach, hon.tensach, hon.soluonghienco, hon.dacdiem, hon.anhsanpham, hon.DVT, hon.dongia,
//            hon.matheloai, hon.manxb, hon.manhanvien
//            order by total_quantity desc""", nativeQuery = true)
//    public List<SACH> BookSellerRevenue(int years, int month);

//    @Query(value = """
//            select top(8) hon.masach, hon.tensach, hon.soluonghienco, hon.dacdiem, hon.anhsanpham, hon.DVT, hon.dongia,
//            hon.matheloai, hon.manxb, hon.manhanvien, sum(ctdh.soluongmua) as total_quantity
//            from SACH hon join CHITIETDATHANG ctdh on hon.masach = ctdh.masach
//            group by hon.masach, hon.tensach, hon.soluonghienco, hon.dacdiem, hon.anhsanpham, hon.DVT, hon.dongia,
//            hon.matheloai, hon.manxb, hon.manhanvien
//            order by total_quantity desc""", nativeQuery = true)
//    public List<SACH> BookSellerRevenue(int years, int monthBG, int monthE);
}
