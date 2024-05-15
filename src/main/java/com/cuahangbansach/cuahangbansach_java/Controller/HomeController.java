package com.cuahangbansach.cuahangbansach_java.Controller;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private HttpSession httpSession;


    @GetMapping("/Home/index")
    public String homepage(Model model) {
        KHACH kyaku = (KHACH) httpSession.getAttribute("guest");
        if (kyaku == null) {

            httpSession.setAttribute("donhang", null);
        } else {
            PHIEUMUAHANG dh = shoppingCartService.GetDH(kyaku.getMakhachhang());
            httpSession.setAttribute("donhang", dh);
            model.addAttribute("guest", kyaku);
        }

        List<THELOAISACH> tl = qltheloaiService.GetAll();
        model.addAttribute("tl", tl);

        return "/Home/index";  // Trả về trang index.html
    }


}
