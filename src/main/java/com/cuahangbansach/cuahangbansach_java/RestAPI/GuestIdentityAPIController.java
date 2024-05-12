package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Exception.UserPassExistedException;
import com.cuahangbansach.cuahangbansach_java.Exception.UserPassNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentitySendMailService;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/api/Guest")
public class GuestIdentityAPIController {
    @Autowired
    private GuestIdentityService guestIdentityService;

    @Autowired
    private GuestIdentitySendMailService guestIdentitySendMailService;

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody @Valid KHACH khach) {
        KHACH guest = guestIdentityService.FindByGuest(khach.getTendangnhap());
        if (guest != null) {
            if(Objects.equals(guest.getMatkhau(), khach.getMatkhau())) {
                return ResponseEntity.ok("Login 完了");
            }
            else throw new UserPassNotFoundException("Tai khoan hoac mat khau khong dung");
        }
        else throw new UserPassNotFoundException("Tai khoan hoac mat khau khong dung");
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
                    guestIdentitySendMailService.Register_ChangePassComplete(guest.getEmail(), "Thông báo đã đổi mật khẩu thành công", guest, "Identity/Guest/ChangePasswordComplete");
                    return ResponseEntity.ok("Doi Mat Khau 完了");
                } catch (Exception ex) {
                    return ResponseEntity.badRequest().body(ex.getMessage());
                }
            }
            else throw new UserPassNotFoundException("Mat khau nhap lai khong dung");
        }
        else throw new UserPassNotFoundException("Tai khoan khong tim thay");
    }
    @PutMapping("/NewUser")
    public ResponseEntity<String> NewUser(@RequestBody @Valid KHACH khach) {
        KHACH guest = guestIdentityService.FindByGuest(khach.getTendangnhap());
        if (guest == null) {
            LocalDateTime dt = LocalDateTime.now();
            KHACH kyaku = new KHACH();
            kyaku.setMakhachhang("KH" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
            kyaku.setTenkhachhang(khach.getTenkhachhang());
            kyaku.setTendangnhap(khach.getTendangnhap());
            kyaku.setMatkhau(khach.getMatkhau());
            kyaku.setDiachi(khach.getDiachi());
            kyaku.setSdt(khach.getSdt());
            kyaku.setEmail(khach.getEmail());

            try{
                guestIdentityService.Save(kyaku);
                guestIdentitySendMailService.Register_ChangePassComplete(khach.getEmail(), "Register Complete", khach, "Identity/Guest/RegisterComplete");
                return ResponseEntity.ok("Register 完了");
            }catch(Exception ex){
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }
        else throw new UserPassExistedException("Tai khoan hoac mat khau ton tai");
    }

    @GetMapping("/GI/{tendangnhap}")
    public ResponseEntity<KHACH> GuestInformation(@PathVariable String tendangnhap) {
        KHACH guest = guestIdentityService.FindByGuest(tendangnhap);
        if(guest != null){
            return ResponseEntity.ok(guest);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/UpdateInformation")
    public ResponseEntity<String> UpdateInformation(@RequestBody @Valid KHACH khach) {
        KHACH guest = guestIdentityService.FindByGuest(khach.getTendangnhap());
        if (guest != null) {
            guest.setTenkhachhang(khach.getTenkhachhang());
            //guest.setMatkhau(khach.getMatkhau());
            guest.setSdt(khach.getSdt());
            guest.setEmail(khach.getEmail());
            guest.setDiachi(khach.getDiachi());
            try {
                guestIdentityService.Save(guest);
                guestIdentitySendMailService.Register_ChangePassComplete(guest.getEmail(), "Update Information Complete", khach, "Identity/Guest/ChangeInformationComplete");
                return ResponseEntity.ok("Update Information 完了");
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        } else throw new UserPassExistedException("Account not Found!!!");

    }

}

