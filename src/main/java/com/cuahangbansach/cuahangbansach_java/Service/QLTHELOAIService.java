package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLSACHRepository;
import com.cuahangbansach.cuahangbansach_java.Repository.QLTHELOAIRepository;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QLTHELOAIService {
    @Autowired
    EntityManager entityManager;

    @Autowired
    private QLTHELOAIRepository qltheloaiRepository;

    @Autowired
    private QLSACHRepository qlsachRepository;

    public List<THELOAISACH> GetAll() {
        return qltheloaiRepository.GetList();
    }

    public List<THELOAISACH> Read() {
        List<THELOAISACH> list = qltheloaiRepository.GetList();
        for (THELOAISACH item : list) {

            String matheloai = item.getMatheloai();
            /*Query query = entityManager.createQuery("SELECT COUNT(s) FROM SACH s WHERE s.matheloai = :matheloai");
            query.setParameter("matheloai", matheloai);
            Long count = (Long) query.getSingleResult();*/
            List<SACH> hon = qlsachRepository.GetSachByMaTL(matheloai);
            int count = hon.size();
            item.setCount(count);
        }

        return list;
    }

    public THELOAISACH GetCategoryById(String id) {
        THELOAISACH tls = qltheloaiRepository.GetCategoryById(id);
        return tls;
    }

}
