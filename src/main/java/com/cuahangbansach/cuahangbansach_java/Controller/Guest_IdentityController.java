package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
public class Guest_IdentityController {

    @Autowired
    private GuestIdentityService guestIdentityService;

    @GetMapping("/Identity/Guest/Login")
    public String Login_Page(Model model) {
        model.addAttribute("user", new KHACH());
        return "/Identity/Guest/Login";
    }

    @PostMapping("/Identity/Guest/Login")
    public String Login(Model model,@RequestBody @Valid @ModelAttribute("user") KHACH khach) {
        KHACH guest = guestIdentityService.FindByGuest(khach.getTendangnhap());
        if (guest != null) {
            if(Objects.equals(guest.getMatkhau(), khach.getMatkhau())) {
                return "redirect:/Home/index";
            }
            else throw new RuntimeException("Tai khoan hoac mat khau khong dung");
        }
        else throw new RuntimeException("Tai khoan hoac mat khau khong dung");
        //return "redirect:/Home/Index";
    }

    @GetMapping("/Identity/Guest/ChangePassword")
    public String ChangePassword(Model model) {
        model.addAttribute("user", new KHACH());
        return "/Identity/Guest/ChangePassword";
    }

    @PostMapping("/Identity/Guest/ChangePassword")
    public String ChangePassword(Model model,@RequestBody @Valid @ModelAttribute("user") KHACH khach) {
        KHACH guest = guestIdentityService.FindByGuest(khach.getTendangnhap());
        if (guest != null) {
            if (Objects.equals(khach.getMatkhau(), khach.getRetypedpass())) {
                guest.setMatkhau(khach.getMatkhau());
                try {
                    guestIdentityService.Save(guest);
                    return "redirect:/Home/index";
                } catch (Exception ex) {
                    return "/Identity/Guest/ChangePassword";
                }
            }
            else throw new RuntimeException("Mat khau nhap lai khong dung");
        }
        else throw new RuntimeException("Tai khoan khong trung khop");

    }

    @GetMapping("/Identity/Guest/NewUser")
    public String NewUserForm(Model model) {
        model.addAttribute("user", new KHACH());
        return "/Identity/Guest/NewUser";
    }
    @PostMapping("/Identity/Guest/Register")
    public String Register (Model model,@RequestBody @Valid @ModelAttribute("user") KHACH khach, @RequestParam("image") MultipartFile multipartFile) {
        //tim kiem ten tai khoan
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

            //them anh
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            //System.out.println(fileName);
            kyaku.setAvatar(fileName);

            try {
                KHACH saveguest = guestIdentityService.Save(kyaku);

                //cap nhat anh vao
                String uploadDir = "./src/main/resources/static/GuestAvatar/" + saveguest.getMakhachhang();
                Path uploadPath =  Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

                try (InputStream inputStream = multipartFile.getInputStream()){
                    Path filePath = uploadPath.resolve(fileName);
                    System.out.println(filePath.toFile().getAbsolutePath());
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);


                } catch (IOException _) {
                    return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thấy";
                }

                return "redirect:/Home/index";
            } catch (Exception ex) {
                return "/Identity/Guest/NewUser";
            }
        }
        else throw new RuntimeException("Tai khoan hoac mat khau ton tai");

    }

    @GetMapping("/Identity/Guest/GuestInfomation")
    public String GuestInfomationForm(Model model){
        model.addAttribute("user", new KHACH());
        return "/Identity/Guest/Information";
    }

    @GetMapping("/Identity/Guest/UpdateInformation")
    public String UpdateInformationForm(Model model) {
        return "/Identity/Guest/Information";
    }

    //post updateinfor

    //get-post updateimage

}
