package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Exception.UserPassExistedException;
import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Repository.StaffIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
            throw new UserPassExistedException("Nguoi dung ton tai");
        }
        LocalDateTime dt = LocalDateTime.now();
        nv.setManhanvien("NV" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
        nv.setTennhanvien(nhanvien.getTennhanvien());
        nv.setEmail(nhanvien.getEmail());
        nv.setTendangnhap(nhanvien.getTendangnhap());
        nv.setMatkhau(nhanvien.getMatkhau());

        return staffIdentityRepository.save(nv);
    }
    public NHANVIEN Save(NHANVIEN nhanvien) {
        staffIdentityRepository.save(nhanvien);
        return nhanvien;
    }

    public void Update(NHANVIEN nhanvien) {
        staffIdentityRepository.save(nhanvien);
    }
}
