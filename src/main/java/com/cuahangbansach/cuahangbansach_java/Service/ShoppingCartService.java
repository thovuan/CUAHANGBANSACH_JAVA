package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.CHITIETDATHANG;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Repository.DetailSCRepository;
import com.cuahangbansach.cuahangbansach_java.Repository.ShoppingCartRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingCartService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private DetailSCRepository scRepository;

    @Autowired
    EntityManager entityManager;

    public List<PHIEUMUAHANG> GetList() {
        return shoppingCartRepository.findAll();
    }

    public List<List<Object>> CartRevenue() {
        List<List<Object>> data = new ArrayList<>();
        //List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByYear(2024);
        long Totalcost = 0;
        for (int i =1 ; i<=12; i++) {

            List<Object> sub = new ArrayList<>();

            sub.add(i);
            sub.add(shoppingCartRepository.Revenue(i, 2024).size());
            data.add(sub);
        }

        System.out.println(data);
        return data;
    }

    public List<PHIEUMUAHANG> GetListByGuestId(String guestId) {
        return shoppingCartRepository.GetPMHByGuestId(guestId);
    }

    public List<PHIEUMUAHANG> GetList2(String id) {
        return shoppingCartRepository.GetPMHById2(id);
    }

    public PHIEUMUAHANG GetDH(String id) {
        return shoppingCartRepository.GetDH(id);
    }

    public PHIEUMUAHANG GetSCDetail(String id) {
        PHIEUMUAHANG pmh = shoppingCartRepository.GetPMHById(id);
        pmh.dsSach = new ArrayList<SACH>();

        if (pmh != null) {
            List<CHITIETDATHANG> ctdh = scRepository.GetListDHId(pmh.getMaphieumuahang());

            for(CHITIETDATHANG dh : ctdh) {
                SACH hon = qlsachService.GetSachById(dh.getMasach().getMasach());

                if (hon != null) {
                    hon.setSoluongmua(dh.getSoluongmua());
                    hon.setThanhtien((long) hon.getDongia() * dh.getSoluongmua());
                    pmh.setDHTotal(pmh.getDHTotal() + hon.getThanhtien());
                    pmh.dsSach.add(hon);
                }
            }
            return pmh;
        }
        return null;
    }

    public PHIEUMUAHANG GetPMHById(String id) {
        return shoppingCartRepository.GetPMHById(id);
    }

    public CHITIETDATHANG GetCHHById(String DHId, String HonId) {
        //return scRepository.GetId(new CHITIETDATHANGKey(DHId, HonId)).orElse(null);
        return scRepository.GetId(DHId, HonId);
    }

    public PHIEUMUAHANG TrackingCart(String id, String email) {
        return shoppingCartRepository.TrackingCart(id, email);
    }

    public PHIEUMUAHANG Create(PHIEUMUAHANG phieumuahang) {

        return shoppingCartRepository.save(phieumuahang);
    }

    public void Add(CHITIETDATHANG chitietdathang) {
        scRepository.save(chitietdathang);
        //return chitietdathang;
    }

    public void Update(CHITIETDATHANG chitietdathang) {
        scRepository.save(chitietdathang);
    }

    @Transactional
    public void UpdateCTDH(CHITIETDATHANG chitietdathang) {

        entityManager.merge(chitietdathang);
        //scRepository.save(chitietdathang);
    }

    public void Delete(PHIEUMUAHANG phieumuahang) {
        shoppingCartRepository.delete(phieumuahang);
    }

    public void DeleteBook(CHITIETDATHANG chitietdathang) {
        scRepository.delete(chitietdathang);
    }

    //public boolean

}
