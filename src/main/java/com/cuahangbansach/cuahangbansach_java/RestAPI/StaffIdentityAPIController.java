package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Exception.AllExceptionHandler;
import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Exception.UserPassNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentitySendMailService;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/Staff")
public class StaffIdentityAPIController {
    @Autowired
    private StaffIdentityService staffIdentityService;

    @Autowired
    private StaffIdentitySendMailService staffIdentitySendMailService;

    @PostMapping
    public ResponseEntity<NHANVIEN> NewStaff(@RequestBody @Valid NHANVIEN nhanvien) {
        return ResponseEntity.ok(staffIdentityService.Add(nhanvien));
    }

    @PostMapping("/Login")
    public ResponseEntity<NHANVIEN> Login(@RequestBody @Valid NHANVIEN nhanvien) {
        NHANVIEN nv = staffIdentityService.FindByUserName(nhanvien.getTendangnhap());
        if (nv != null) {
            if (Objects.equals(nv.getMatkhau(), nhanvien.getMatkhau())) {
                return ResponseEntity.ok(nv);
            }
            else throw new UserPassNotFoundException("User or Password isn't correct");
        }
        else throw new UserPassNotFoundException("User or Password isn't correct");
        //return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/Changepass")
    public ResponseEntity<String> ChangePassword(@RequestBody @Valid NHANVIEN nhanvien) {
        NHANVIEN nv = staffIdentityService.FindByUserName(nhanvien.getTendangnhap());
        if (nv != null) {
            if (Objects.equals(nhanvien.getRetypedpass(), nhanvien.getMatkhau())) {
                nv.setMatkhau(nhanvien.getMatkhau());

                try {
                    staffIdentityService.Update(nv);
                    return ResponseEntity.ok("Change Password 完了");

                } catch (Exception ex){
                    return ResponseEntity.badRequest().body(ex.toString());
                }

            }
            else throw new UserPassNotFoundException("mật khẩu nhap lai khong trung khop");
        }
        else throw new UserPassNotFoundException("Tài khoản khong tim thay");
    }

    @PostMapping("/Register")
    public ResponseEntity<String> Register(@RequestBody @Valid NHANVIEN nhanvien) {
                try {
                    staffIdentityService.Add(nhanvien);
                    staffIdentitySendMailService.Register_ChangePassComplete(nhanvien.getEmail(), "Register Complete", nhanvien, "Identity/Admin/RegisterComplete");
                    return ResponseEntity.ok("Add Staff User 完了");

                } catch (Exception ex){
                    return ResponseEntity.badRequest().body("Loi khi them/ sua/ xoa: \n" + ex.toString());
                }

//          staffIdentityService.Add(nhanvien);
//            return ResponseEntity.ok("New User 完了");
    }

    @PostMapping("/ChangeInformation/{id}")
    public ResponseEntity<String> ChangeInformation(@PathVariable String id, @RequestBody @Valid NHANVIEN nhanvien) {
        NHANVIEN nv = staffIdentityService.FindByUserName(nhanvien.getTendangnhap());
        if (nv != null) {
            nv.setTennhanvien(nhanvien.getTennhanvien());

            staffIdentityService.Update(nv);
            return ResponseEntity.ok("Update Information 完了");
        }
        else throw new ResourceNotFoundException("Nhan vien not found: " + nhanvien.getTendangnhap());

    }

    @GetMapping("/SI/{tendangnhap}")
    public ResponseEntity<NHANVIEN> StaffInformation(@PathVariable String tendangnhap) {
        NHANVIEN nv = staffIdentityService.FindByUserName(tendangnhap);
        if (nv!=null) {
            return ResponseEntity.ok(nv);
        } return ResponseEntity.badRequest().body(null);
    }
}
