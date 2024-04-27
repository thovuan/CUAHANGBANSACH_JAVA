package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Repository.QLKHACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QLKHACHService {
    @Autowired
    private QLKHACHRepository qlkhachRepository;

    public KHACH getKhachById(String id) {
        KHACH k = qlkhachRepository.GetById(id);
        if (k == null) {
            return null;
        }
        return k;
    }
}
