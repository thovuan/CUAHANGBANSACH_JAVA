package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG_KHACH;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrackingController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/Tracking/Index")
    public String Tracking(Model model) {
        model.addAttribute("phieumuahang", new PHIEUMUAHANG_KHACH());
        model.addAttribute("guest_no_cart", (PHIEUMUAHANG) httpSession.getAttribute("pmh"));
        return "Tracking/Index";
    }

    @PostMapping("/Tracking/Index")
    public String TrackingCart(@ModelAttribute("phieumuahang")PHIEUMUAHANG_KHACH pmh_k) {
        PHIEUMUAHANG pmh = shoppingCartService.TrackingCart(pmh_k.getMaphieumuahang(), pmh_k.getEmail());

        if (pmh != null) {
            httpSession.setAttribute("pmh", pmh);
            return "redirect:/Tracking/Index";
        } else throw new ResourceNotFoundException("Data Not Found");
    }
}
