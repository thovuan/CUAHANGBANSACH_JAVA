package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Service.EmailSenderService;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentitySendMailService;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentityService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class GuestIdentityController {

    @Autowired
    private GuestIdentityService guestIdentityService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private GuestIdentitySendMailService guestIdentitySendMailService;

    @Autowired
    private HttpSession httpSession;

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
                //emailSenderService.sendmail(guest.getEmail(), "Login Successfully", "ログイン完了");
                httpSession.setAttribute("guest", guest);

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
                    guestIdentitySendMailService.Register_ChangePassComplete(guest.getEmail(), "Thông báo đã đổi mật khẩu thành công", guest, "Identity/Guest/ChangePasswordComplete");
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
                guestIdentitySendMailService.Register_ChangePassComplete(khach.getEmail(), "Register Complete", khach, "Identity/Guest/RegisterComplete");
                return "redirect:/Home/index";
            } catch (Exception ex) {
                return "/Identity/Guest/NewUser";
            }
        }
        else throw new RuntimeException("Tai khoan hoac mat khau ton tai");

    }

    @GetMapping("/Identity/Guest/Logout")
    public String Logout (Model model) {
        httpSession.removeAttribute("guest");
        httpSession.removeAttribute("donhang");
        return "redirect:/Home/index";
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
