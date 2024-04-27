package com.cuahangbansach.cuahangbansach_java.Controller;


import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.QLKHACHService;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private QLKHACHService qlkhachService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/ShoppingCart/Index")
    public String Index(Model model) {
        List<PHIEUMUAHANG> list = shoppingCartService.GetList();
        model.addAttribute("SCL", list);
        return "ShoppingCart/Index";
    }
    @GetMapping("/ShoppingCart/Create")
    public String Create (Model model) {
        LocalDateTime dt = LocalDateTime.now();
        PHIEUMUAHANG pmh = new PHIEUMUAHANG();
        pmh.setMaphieumuahang("DH" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
        pmh.setNgaylap(dt);
        pmh.setTinhtrangthanhtoan("Chưa thanh toán");
        pmh.setTinhtrang("Chưa xác nhận");
        pmh.setKhach(qlkhachService.getKhachById("KH01"));
        try {
            shoppingCartService.Create(pmh);
            httpSession.setAttribute("donhang", pmh);
            return "redirect:/ShoppingCart/Index";

        } catch (Exception ex) {
            return "Loi";
        }

    }
}
