package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.CHITIETCHUCVU;
import com.cuahangbansach.cuahangbansach_java.Model.CHUCVU;
import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Repository.CTCVRepository;
import com.cuahangbansach.cuahangbansach_java.Repository.QLCVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QLCVService {

    @Autowired
    private QLCVRepository qlcvRepository;

    @Autowired
    private CTCVRepository ctcvRepository;

    public List<CHUCVU> getList() {
        return qlcvRepository.findAll();
    }

    public CHITIETCHUCVU findbyId(String manv, String madh) {
        return ctcvRepository.findByChitietchucvu(manv,madh);
    }

    public void AddCV(CHITIETCHUCVU ctcv) {
        ctcvRepository.save(ctcv);
    }

    public void DeleteCV(CHITIETCHUCVU ctcv) {
        ctcvRepository.delete(ctcv);
    }
}
