package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.EmailSenderService;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QLDONHANGController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private EmailSenderService mailSenderService;

    @GetMapping("/QLDONHANG/QLDONHANG/Index")
    public String Index(Model model, @RequestParam(name = "dhid", required = false)String pmh) {

        List<PHIEUMUAHANG> list;
        if (pmh != null) {
            list = shoppingCartService.GetList2(pmh);
            model.addAttribute("SCL", list);
        } else {

                list = shoppingCartService.GetList();
                model.addAttribute("SCL", list);

        }

        return "/QLDONHANG/QLDONHANG/Index";
    }

    @GetMapping("/QLDONHANG/QLDONHANG/Detail/{id}")
    public String Detail(Model model, @PathVariable String id) {
        PHIEUMUAHANG pmh = shoppingCartService.GetSCDetail(id);
        if (pmh == null) {return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";}

        model.addAttribute("pmh", pmh);
        return "/QLDONHANG/QLDONHANG/Detail";
    }

    /*@GetMapping("/QLDONHANG/QLDONHANG/Confirm/{id}")
    public String CheckForm(Model model, @PathVariable String id) {
        PHIEUMUAHANG pmh = shoppingCartService.GetSCDetail(id);
        if (pmh == null) {return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";}

        model.addAttribute("pmh", pmh);
        return "/QLDONHANG/QLDONHANG/Check";
    }*/

    @PostMapping("/QLDONHANG/QLDONHANG/Confirm/{id}")
    public String Confirm(Model model, @PathVariable String id){
        PHIEUMUAHANG pmh = shoppingCartService.GetPMHById(id);
        if (pmh == null) return "Loi";

        pmh.setTinhtrang("Đã tiếp nhận");

        try {
            shoppingCartService.Create(pmh);
            mailSenderService.sendCartConfirmMail(pmh.getKhach().getEmail(), "Xác nhận đã tiếp nhận đơn hàng #"+pmh.getMaphieumuahang(), pmh, pmh.getKhach(), "ShoppingCart/OrderCompleteMail" );

            return "redirect:/QLDONHANG/QLDONHANG/Index";
        } catch (Exception ex) {
            return "redirect:/Error/ErrorMe?mess=" + ex.getMessage();
        }

    }
}
