package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/Staff")
public class StaffIdentityAPIController {
    @Autowired
    private StaffIdentityService staffIdentityService;

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
            else throw new RuntimeException("Tài khoản hoặc mật khẩu khong dung");
        }
        else throw new RuntimeException("Tài khoản hoặc mật khẩu khong dung");
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
            else throw new RuntimeException("mật khẩu nhap lai khong trung khop");
        }
        else throw new RuntimeException("Tài khoản khong tim thay");
    }
}
