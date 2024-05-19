package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Exception.UserPassExistedException;
import com.cuahangbansach.cuahangbansach_java.Exception.UserPassNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.CHITIETCHUCVU;
import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Repository.StaffIdentityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class StaffIdentityService {
    @Autowired
    private StaffIdentityRepository staffIdentityRepository;

    public NHANVIEN FindByUserName(String userName) {
        return staffIdentityRepository.findByUserName(userName);
    }

//    @Transactional
//    public UserDetails loadUserByUsername(String username) {
//        NHANVIEN user = staffIdentityRepository.findByUserName(username);
//        if (user != null) {
//            String password = user.getMatkhau();
//
//
//            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//            for (CHITIETCHUCVU chucVuDetail : user.getChucVuDetails()) {
//                authorities.add(new SimpleGrantedAuthority(chucVuDetail.getMachucvu().getTenchucvu()));
//            }
//
//            return new org.springframework.security.core.userdetails.User(user.getTendangnhap(), user.getMatkhau(), authorities);
//
//        } else {
//            throw new UserPassNotFoundException(
//                    "Unable to find user with username provided!!");
//        }
//    }

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
