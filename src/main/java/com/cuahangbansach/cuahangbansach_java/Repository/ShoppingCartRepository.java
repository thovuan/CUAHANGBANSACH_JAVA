package com.cuahangbansach.cuahangbansach_java.Repository;


import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;

import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<PHIEUMUAHANG, String> {
    @Query(value="select * from PHIEUMUAHANG where maphieumuahang = :id", nativeQuery = true)
    public PHIEUMUAHANG GetPMHById(String id);

    @Query(value="select * from PHIEUMUAHANG where maphieumuahang like %:id%", nativeQuery = true)
    public List<PHIEUMUAHANG> GetPMHById2(String id);

    @Query(value="select Top(1) * from PHIEUMUAHANG where tinhtrang = N'Chưa xác nhận' and makhachhang = :id", nativeQuery = true)
    public PHIEUMUAHANG GetDH(String id);

    @Query(value="select * from PHIEUMUAHANG where makhachhang = :id", nativeQuery = true)
    public List<PHIEUMUAHANG> GetPMHByGuestId(String id);

    @Query(value="select pmh.* from PHIEUMUAHANG pmh inner join KHACH guest on pmh.makhachhang = guest.makhachhang where pmh.maphieumuahang = :id and guest.email = :email", nativeQuery = true)
    public PHIEUMUAHANG TrackingCart(String id, String email);

    @Query(value = "select * from PHIEUMUAHANG where year(ngaylap)  = :years  and month(ngaylap)  = :months", nativeQuery = true)
    public List<PHIEUMUAHANG> Revenue(int months, int years);
}


