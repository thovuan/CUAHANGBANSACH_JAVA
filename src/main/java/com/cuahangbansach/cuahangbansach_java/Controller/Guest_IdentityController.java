package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Guest_IdentityController {

    @GetMapping("/Identity/Guest/Login")
    public String Login_Page(Model model) {
        model.addAttribute("login", new KHACH());
        return "/Identity/Guest/Login";
    }

    @PostMapping("/Identity/Guest/Login")
    public String Login(Model model, KHACH khach) {
        return "redirect:/Home/Index";
    }
}
