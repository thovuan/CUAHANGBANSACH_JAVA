//package com.cuahangbansach.cuahangbansach_java.Service;
//
//import com.cuahangbansach.cuahangbansach_java.Exception.UserPassNotFoundException;
//import com.cuahangbansach.cuahangbansach_java.Model.CHITIETCHUCVU;
//import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
//import com.cuahangbansach.cuahangbansach_java.Repository.StaffIdentityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class CustomStaffDetailsService implements UserDetailsService {
//    @Autowired
//    private StaffIdentityRepository staffIdentityRepository;
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String tendangnhap) throws UserPassNotFoundException {
//        NHANVIEN nv = staffIdentityRepository.findByUserName(tendangnhap);
//        if(nv==null)
//            throw new UserPassNotFoundException("Staff Not Found");
//
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        for (CHITIETCHUCVU chucVuDetail : nv.getChucVuDetails()) {
//            authorities.add(new SimpleGrantedAuthority(chucVuDetail.getMachucvu().getTenchucvu()));
//        }
//
//        //return new org.springframework.security.core.userdetails.User(nv.getTendangnhap(), nv.getMatkhau(), authorities);
//        return User.builder()
//                .username(nv.getTendangnhap())
//                .password(nv.getMatkhau())
//                .authorities(String.valueOf(authorities)).build();
//    }
//}
