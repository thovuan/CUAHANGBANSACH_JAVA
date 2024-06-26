package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Exception.UserPassExistedException;
import com.cuahangbansach.cuahangbansach_java.Exception.UserPassNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;

import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentitySendMailService;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller

public class StaffIdentityController {
    @Autowired
    private StaffIdentityService staffIdentityService;

//    @Autowired
//    private CustomStaffDetailsService customStaffDetailsService;

    @Autowired
    private StaffIdentitySendMailService staffIdentitySendMailService;

    @Autowired
    private HttpSession httpSession;



    @GetMapping("/Identity/Admin/Login")
    public String LoginForm(Model model) {
        model.addAttribute("staff", new NHANVIEN());
        return "/Identity/Admin/Login";
    }
//
    @PostMapping("/Identity/Admin/Login")
    public String Login(@RequestBody @Valid @ModelAttribute("staff") NHANVIEN nhanvien, Model model) {

        NHANVIEN nv = staffIdentityService.FindByUserName(nhanvien.getTendangnhap());
        if (nv != null) {
            if (Objects.equals(nv.getMatkhau(), nhanvien.getMatkhau())) {
                httpSession.setAttribute("nv", nv);
                return "redirect:/NeoHome/Index";
            }
            //else throw new UserPassNotFoundException("User or Password isn't correct");
            model.addAttribute("errorMessage", "Tai khoan hoac mat khau khong dung");
            return "/Identity/Admin/Login";
        }
        //else throw new UserPassNotFoundException("User or Password isn't correct");
        model.addAttribute("errorMessage", "Tai khoan hoac mat khau khong dung");
        return "/Identity/Admin/Login";
    }

    @GetMapping("/Identity/Admin/Register")
    public String Register_Form(Model model) {
        model.addAttribute("nv", new NHANVIEN());
        return "/Identity/Admin/Register";
    }

    @PostMapping("/Identity/Admin/Register")
    public String Register(@RequestBody @Valid @ModelAttribute("nv") NHANVIEN nhanvien, @RequestParam("image") MultipartFile multipartFile, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/Identity/Admin/Register";
        }

        //tim kiem neu ten dang nhap trung khop
        if (staffIdentityService.FindByUserName(nhanvien.getTendangnhap()) !=null) {
            //throw new UserPassExistedException("Nguoi dung ton tai");
            model.addAttribute("errorMessage", "Nguoi dung ton tai");
            return "/Identity/Admin/Register";
        }

        //them thong tin nhan vien
        NHANVIEN nv = new NHANVIEN();
        LocalDateTime dt = LocalDateTime.now();
        nv.setManhanvien("NV" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
        nv.setTennhanvien(nhanvien.getTennhanvien());
        nv.setEmail(nhanvien.getEmail());
        nv.setTendangnhap(nhanvien.getTendangnhap());
        nv.setMatkhau(nhanvien.getMatkhau());

        //them anh
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //System.out.println(fileName);
        nv.setAvatar(fileName);

        try {
            NHANVIEN savestaff = staffIdentityService.Save(nv);

            String uploadDir = "./src/main/resources/static/StaffAvatar/" + savestaff.getManhanvien();
            Path uploadPath =  Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            try (InputStream inputStream = multipartFile.getInputStream()){
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException _) {
                return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thấy";
            }

            staffIdentitySendMailService.Register_ChangePassComplete(nhanvien.getEmail(), "Register Complete", nhanvien, "Identity/Admin/RegisterComplete");
            return "redirect:/Identity/Admin/Login";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "/Identity/Admin/Register";
        }
    }

    @PostMapping("/Identity/Admin/UploadAvatar")
    public String UploadAvatar(Model model, @RequestParam("image")MultipartFile multipartFile) {
        NHANVIEN nv = (NHANVIEN) httpSession.getAttribute("nv");

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //System.out.println(fileName);
        nv.setAvatar(fileName);
        try {

            NHANVIEN save = staffIdentityService.Save(nv);

            String uploadDir = "./src/main/resources/static/StaffAvatar/" + save.getManhanvien();
            Path uploadPath =  Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            try (InputStream inputStream = multipartFile.getInputStream()){
                //cap nhat anh vao

                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

                return "redirect:/Identity/Admin/StaffInformation";

            } catch (IOException _) {
                model.addAttribute("errorMessage", "Lỗi thêm ảnh đại diện");
                return "/Identity/Admin/Information";
            }

        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Lỗi thêm ảnh đại diện");
            return "/Identity/Admin/Information";
        }


    }

    @GetMapping("/Identity/Admin/Logout")
    public String Logout() {
        httpSession.removeAttribute("nv");
        return "redirect:/Identity/Admin/Login";
    }

    @GetMapping("/Identity/Admin/ChangePassword")
    public String ChangePasswordForm(Model model) {
        model.addAttribute("user", new NHANVIEN());
        return "/Identity/Admin/ChangePassword";
    }

    @PostMapping("/Identity/Admin/ChangePassword")
    public String ChangePassword(Model model, @RequestBody @Valid @ModelAttribute("user")NHANVIEN nhanvien, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
          return "/Identity/Admin/ChangePassword";
       }

        NHANVIEN nv = staffIdentityService.FindByUserName(nhanvien.getTendangnhap());
        if (nv != null) {
            if (Objects.equals(nhanvien.getRetypedpass(), nhanvien.getMatkhau())) {
                nv.setMatkhau(nhanvien.getMatkhau());

                try {
                    staffIdentityService.Update(nv);
                    staffIdentitySendMailService.Register_ChangePassComplete(nv.getEmail(), "Update Password", nhanvien, "Identity/Admin/ChangePasswordComplete");
                    return "redirect:/Identity/Admin/Login";

                } catch (Exception ex){
                    model.addAttribute("errorMessage", ex.getMessage());
                    return "/Identity/Admin/ChangePassword";
                    //return "redirect:/Error/ErrorMe?mess=" + ex.toString();
                }

            }
            //else throw new UserPassNotFoundException("mật khẩu nhap lai khong trung khop");
            model.addAttribute("errorMessage", "mật khẩu nhap lai khong trung khop");
            return "/Identity/Admin/ChangePassword";
        }
        //else throw new UserPassNotFoundException("Tài khoản khong tim thay");
        model.addAttribute("errorMessage", "Tài khoản khong tim thay");
        return "/Identity/Admin/ChangePassword";
    }

    @GetMapping("/Identity/Admin/StaffInformation")
    public String StaffInformation(Model model) {
        NHANVIEN nv = (NHANVIEN) httpSession.getAttribute("nv");

        model.addAttribute("nv", nv);
        //return "/Identity/Guest/Information";
        return "/Identity/Admin/Information";
    }
}
