package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Repository.StaffIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffIdentityService {
    @Autowired
    private StaffIdentityRepository staffIdentityRepository;

    public NHANVIEN FindByUserName(String userName) {
        return staffIdentityRepository.findByUserName(userName);
    }

    public NHANVIEN Add(NHANVIEN nhanvien) {
        NHANVIEN nv = new NHANVIEN();

        if (FindByUserName(nhanvien.getTendangnhap()) !=null) {
            throw new RuntimeException("Nguoi dung ton tai");
        }

        nv.setManhanvien(nhanvien.getManhanvien());
        nv.setTennhanvien(nhanvien.getTennhanvien());
        nv.setTendangnhap(nhanvien.getTendangnhap());
        nv.setMatkhau(nhanvien.getMatkhau());

        return staffIdentityRepository.save(nv);
    }

    public void Update(NHANVIEN nhanvien) {
        staffIdentityRepository.save(nhanvien);
    }
}
