package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.Blog;
import com.cuahangbansach.cuahangbansach_java.Model.BookComment;
import com.cuahangbansach.cuahangbansach_java.Model.CHITIETDATHANG;
import com.cuahangbansach.cuahangbansach_java.Repository.BookCommentRepository;
import com.cuahangbansach.cuahangbansach_java.Repository.DetailSCRepository;
import com.cuahangbansach.cuahangbansach_java.Repository.QLSACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class QLSACHService {
    EntityManager entityManager;
    @Autowired
    public QLSACHRepository ql;

    @Autowired
    private DetailSCRepository scRepository;

    @Autowired
    private BookCommentRepository bookCommentRepository;

    @Autowired
    private DetailSCRepository scDetailRepository;


    public List<SACH> getList() {
        List<SACH> saches = ql.GetList();

        for (SACH sach : saches) {
            try {
                /*String tentheloai = (String) entityManager.createNativeQuery("SELECT tentheloai FROM  THELOAISACH where matheloai = :matheloai")
                        .setParameter("matheloai", sach.getMatheloai()).getSingleResult();*/



                String tentheloai = sach.getMatheloai().getTentheloai();
                String tennxb = sach.getManxb().getTennxb();
                String tennv = sach.getManhanvien().getTennhanvien();

                sach.setTentheloai(tentheloai);
                sach.setTennxb(tennxb);
                sach.setTennhanvien(tennv);


            } catch (NoResultException ex) {
                sach.setTentheloai("Shiranai");
                sach.setTennxb("Shiranai");
                sach.setTennhanvien("Shiranai");
            }

        }
        return saches;

    }

    public SACH GetSachById(String id) {
        SACH sach = ql.GetSachById(id);
        if (sach == null) { return null;}

        try {
                /*String tentheloai = (String) entityManager.createNativeQuery("SELECT tentheloai FROM  THELOAISACH where matheloai = :matheloai")
                        .setParameter("matheloai", sach.getMatheloai()).getSingleResult();*/

            String tentheloai = sach.getMatheloai().getTentheloai();
            String tennxb = sach.getManxb().getTennxb();
            String tennv = sach.getManhanvien().getTennhanvien();

            sach.setTentheloai(tentheloai);
            sach.setTennxb(tennxb);
            sach.setTennhanvien(tennv);


        } catch (NoResultException ex) {
            sach.setTentheloai("Shiranai");
            sach.setTennxb("Shiranai");
            sach.setTennhanvien("Shiranai");
        }
        return sach;
    }

    public List<SACH> getSACHbyMaTL (String maTl) {
        List<SACH> hon = ql.GetSachByMaTL(maTl);
        if (hon == null) { return null;}
        return hon;
    }

    public List<SACH> getSACHbyMaNXB (String maNXB) {
        List<SACH> hon = ql.GetSachByMaNXB(maNXB);
        if (hon == null) { return null;}
        return hon;
    }

    public List<SACH> getSACHByName(String name) {
        List<SACH> hon = ql.GetSachbyName(name);
        for (SACH sach : hon) {
            try {
                /*String tentheloai = (String) entityManager.createNativeQuery("SELECT tentheloai FROM  THELOAISACH where matheloai = :matheloai")
                        .setParameter("matheloai", sach.getMatheloai()).getSingleResult();*/

                String tentheloai = sach.getMatheloai().getTentheloai();
                String tennxb = sach.getManxb().getTennxb();
                String tennv = sach.getManhanvien().getTennhanvien();

                sach.setTentheloai(tentheloai);
                sach.setTennxb(tennxb);
                sach.setTennhanvien(tennv);


            } catch (NoResultException ex) {
                sach.setTentheloai("Shiranai");
                sach.setTennxb("Shiranai");
                sach.setTennhanvien("Shiranai");
            }
        }
        return hon;
    }

    public List<SACH> Top8Seller() {
        List<CHITIETDATHANG> ct = scRepository.findAll();

        Map<String, Integer> booksCountMap = new HashMap<>();

        for (CHITIETDATHANG cht : ct) {
            String BookId = cht.getMasach().getMasach();
            int quantity = cht.getSoluongmua();

            if (booksCountMap.containsKey(BookId)) {
                booksCountMap.put(BookId, booksCountMap.get(BookId)+quantity);
            } else {
                booksCountMap.put(BookId, quantity);
            }
        }

        List<SACH> productList = booksCountMap.entrySet().stream()
                .map(entry -> new SACH()).sorted((p1, p2) -> Integer.compare(p2.getSoluongmua(), p1.getSoluongmua())).collect(Collectors.toList());

        // Sort the product list based on quantitySold in descending order

        // Get the top 8 best selling products
        int numTopProducts = Math.min(productList.size(), 8);

        return productList.subList(0, numTopProducts);
    }

    public List<SACH> Top8BookSeller() {
        return ql.Top8SellerBook();
    }

    public List<List<Object>> GetBookSellerRevenue(int year) {
        List<List<Object>> data = new ArrayList<>();
        //List<PHIEUMUAHANG> listDHbyYear = revenueStatisticsRepository.getRevenueStatisticsByYear(2024);
        for (int i =1 ; i<=12; i++) {
            List<CHITIETDATHANG> listSACH = scDetailRepository.BookSellerRevenue(2024, i);
            List<Object> sub = new ArrayList<>();
            long soluongmua = 0;
            for (CHITIETDATHANG cht : listSACH) {
                soluongmua+=cht.getSoluongmua();
            }
            sub.add(i);
            sub.add(soluongmua);
            data.add(sub);
        }
//        data.add(Arrays.asList(1,2024));
//        data.add(Arrays.asList(2,2026));
        System.out.println(data);
        return data;
    }

    public void  Delete(String id) {
        ql.deleteById(id);
        //return id;

    }

    public SACH Create(SACH sach) {
        ql.save(sach);
        return sach;
    }

    public void Add(BookComment bookComment) {
        bookCommentRepository.save(bookComment);
    }

//    public List<SACH> getSACHs(int page, int pageSize) {
//        int offset = (page-1)*pageSize;
//        return ql.fi
//    }

}
