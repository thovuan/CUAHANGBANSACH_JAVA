package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/Guest")
public class GuestIdentityAPIController {
    @Autowired
    private GuestIdentityService guestIdentityService;

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody @Valid KHACH khach) {
        KHACH guest = guestIdentityService.FindByGuest(khach.getTendangnhap());
        if (guest != null) {
            if(Objects.equals(guest.getTendangnhap(), khach.getTendangnhap())) {
                return ResponseEntity.ok("Login 完了");
            }
            else throw new RuntimeException("Tai khoan hoac mat khau khong dung");
        }
        else throw new RuntimeException("Tai khoan hoac mat khau khong dung");
        //return null;
    }

    @PostMapping("/ChangePass")
    public ResponseEntity<String> ChangePass(@RequestBody @Valid KHACH khach) {
        KHACH guest = guestIdentityService.FindByGuest(khach.getTendangnhap());
        if (guest != null) {
            if (Objects.equals(khach.getMatkhau(), khach.getRetypedpass())) {
                guest.setMatkhau(khach.getMatkhau());
                try {
                    guestIdentityService.Save(guest);
                    return ResponseEntity.ok("Doi Mat Khau 完了");
                } catch (Exception ex) {
                    return ResponseEntity.badRequest().body(ex.getMessage());
                }
            }
            else throw new RuntimeException("Mat khau nhap lai khong dung");
        }
        else throw new RuntimeException("Tai khoan khong trung khop");
    }
}
