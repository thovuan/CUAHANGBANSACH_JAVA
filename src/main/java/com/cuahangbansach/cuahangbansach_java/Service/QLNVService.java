package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Repository.QLNVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QLNVService {

    @Autowired
    private QLNVRepository qlnvRepository;

    public NHANVIEN GetById(String id) {
        return qlnvRepository.GetById(id);
    }

    public List<NHANVIEN> getList() {
        return qlnvRepository.findAll();
    }

    public List<NHANVIEN> getName(String staffname) {
        return qlnvRepository.getName(staffname);
    }

    public void Delete(String id) {
        qlnvRepository.deleteById(id);
    }

}
