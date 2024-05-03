package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Repository.QLSACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QLSACHService {
    EntityManager entityManager;
    @Autowired
    public QLSACHRepository ql;

    public List<SACH> getList() {
        List<SACH> saches = ql.GetList();

        for (SACH sach : saches) {
            try {
                /*String tentheloai = (String) entityManager.createNativeQuery("SELECT tentheloai FROM  THELOAISACH where matheloai = :matheloai")
                        .setParameter("matheloai", sach.getMatheloai()).getSingleResult();*/



                String tentheloai = sach.getTheloaisach().getTentheloai();
                String tennxb = sach.getNxb().getTennxb();
                String tennv = sach.getNhanvien().getTennhanvien();

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

            String tentheloai = sach.getTheloaisach().getTentheloai();
            String tennxb = sach.getNxb().getTennxb();
            String tennv = sach.getNhanvien().getTennhanvien();

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

                String tentheloai = sach.getTheloaisach().getTentheloai();
                String tennxb = sach.getNxb().getTennxb();
                String tennv = sach.getNhanvien().getTennhanvien();

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

    public void  Delete(String id) {
        ql.deleteById(id);
        //return id;

    }

    public SACH Create(SACH sach) {
        ql.save(sach);
        return sach;
    }


}
