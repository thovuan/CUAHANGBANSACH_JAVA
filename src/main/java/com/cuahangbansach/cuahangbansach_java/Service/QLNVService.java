package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Repository.QLNVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QLNVService {

    @Autowired
    private QLNVRepository qlnvRepository;

    public NHANVIEN GetById(String id) {
        NHANVIEN nv = qlnvRepository.GetById(id);
        return nv;
    }
}
