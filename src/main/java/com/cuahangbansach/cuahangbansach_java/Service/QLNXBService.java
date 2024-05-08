package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLNXBRepository;
import com.cuahangbansach.cuahangbansach_java.Repository.QLSACHRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QLNXBService {
    @Autowired
    EntityManager entityManager;

    @Autowired
    private QLNXBRepository qlnxbRepository;

    @Autowired
    private QLSACHRepository qlsachRepository;

    public List<NXB> GetList() {
        return qlnxbRepository.findAll();
    }

    public List<NXB> Read() {
        List<NXB> list = qlnxbRepository.findAll();
        for (NXB item : list) {
            String manxb= item.getManxb();
            /*Query query = entityManager.createQuery("SELECT COUNT(s) FROM SACH s WHERE s.manxb = :manxb");
            query.setParameter("manxb", manxb);
            Long count = (Long) query.getSingleResult();*/
            List<SACH> hon = qlsachRepository.GetSachByMaNXB(manxb);
            int count = hon.size();
            item.setCount(count);
        }
        return list;
    }

    public NXB GetNXBById(String id) {
        NXB nxb = qlnxbRepository.GetNXBById(id);
        return nxb;
    }

    public void Save(NXB items) {
        qlnxbRepository.save(items);
    }

    public void Delete(NXB items) {
        qlnxbRepository.delete(items);
    }


}
