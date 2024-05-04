package com.cuahangbansach.cuahangbansach_java.Controller;


import com.cuahangbansach.cuahangbansach_java.Model.*;
import com.cuahangbansach.cuahangbansach_java.Service.CardService;
import com.cuahangbansach.cuahangbansach_java.Service.QLKHACHService;
import com.cuahangbansach.cuahangbansach_java.Service.QLSACHService;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private QLKHACHService qlkhachService;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private CardService cardService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/ShoppingCart/Index")
    public String Index(Model model, @RequestParam(name = "dhid", required = false)String pmh) {
        List<PHIEUMUAHANG> list;
        if (pmh != null) {
            list = shoppingCartService.GetList2(pmh);
            model.addAttribute("SCL", list);
        } else {
            list = shoppingCartService.GetList();
            model.addAttribute("SCL", list);
        }

        return "ShoppingCart/Index";
    }

    @GetMapping("/ShoppingCart/Details/{id}")
    public String Detail(Model model, @PathVariable String id) {
        PHIEUMUAHANG pmh = shoppingCartService.GetSCDetail(id);
        if (pmh == null) {return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";}

        model.addAttribute("pmh", pmh);
        return "/ShoppingCart/Details";
    }

    @GetMapping("/ShoppingCart/MyCart")
    public String OreNoCart(Model model) {
        PHIEUMUAHANG dh = shoppingCartService.GetDH("KH01");
        httpSession.setAttribute("donhang", dh);
        model.addAttribute("pmh", dh);
        return "/ShoppingCart/MyCart";
    }

    @GetMapping("/ShoppingCart/Create")
    public String Create (Model model) {
        PHIEUMUAHANG dh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");
        if (dh != null) return "redirect:/Error/ErrorMe?mess=" + "Không the tạo đơn hàng";
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

    @GetMapping("/ShoppingCart/CreateDH")
    public String CreateDH (Model model) {
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
            return "redirect:/SHOP/AddToCart";

        } catch (Exception ex) {
            return "Loi";
        }

    }

    @GetMapping("/ShoppingCart/EditSLBook/{id}")
    public String EditSLBook(Model model, @PathVariable String id) {
        PHIEUMUAHANG pmh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");
        CHITIETDATHANG ctdh = shoppingCartService.GetCHHById(pmh.getMaphieumuahang(), id);
        if (ctdh!=null) {
            CHITIETDATHANG ct = new CHITIETDATHANG();
            ct.setPhieumuahang(ctdh.getPhieumuahang());
            ct.setSach(ctdh.getSach());
            ct.setSoluongmua(ctdh.getSoluongmua());
            ct.setTinhtranggiao(ctdh.getTinhtranggiao());
            model.addAttribute("SACH", ct);
            return "/ShoppingCart/EditSL";
        }
        return "redirect:/Error/ErrorMe?mess=" + "Error";
    }

    @PostMapping("/ShoppingCart/EditSL")
    public  String EditSL(Model model, @ModelAttribute("SACH") CHITIETDATHANG sach) {
        SACH hon = qlsachService.GetSachById(sach.getSach().getMasach());
        PHIEUMUAHANG pmh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");

        //tao them don hang
        if (pmh==null) {
            //return "redirect:/ShoppingCart/CreateDH";
            return "redirect:/ShoppingCart/CreateDH";
        }

        //return "redirect:/ErrorMe?mess=" + sl;

        //neu soluong mua vuot qua so luong hien co -> bao loi
        if (hon.getSoluonghienco() < sach.getSoluongmua()) {
            return "redirect:/Error/ErrorMe?mess=" + "So luong mua khong vuot qua so luong hien tai";
        }

        //tien hanh them sach vao
        CHITIETDATHANG ct = shoppingCartService.GetCHHById(sach.getPhieumuahang().getMaphieumuahang(), sach.getSach().getMasach());
        //ct.setPhieumuahang(shoppingCartService.GetPMHById(pmh.getMaphieumuahang()));
        //ct.setSach(hon);
        ct.setSoluongmua(sach.getSoluongmua());
        //ct.setTinhtranggiao("Chưa giao");
        //model.addAttribute("Error", "Số lượng mua vượt qua số lượng hiện c");

        try {
            shoppingCartService.UpdateCTDH(ct);
            //return "redirect:/SHOP/Index";
            return "redirect:/ShoppingCart/Index";

        } catch (Exception ex) {
            return "redirect:/Error/ErrorMe?mess=" + ex.getMessage();
        }
    }

    @GetMapping("/ShoppingCart/Confirm/{id}")
    public String Confirm(Model model, @PathVariable String id) {
        PHIEUMUAHANG pmh = shoppingCartService.GetSCDetail(id);
        if (pmh == null) {return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";}

        model.addAttribute("pmh", pmh);
        return "ShoppingCart/Confirm";
    }

    @PostMapping("/ShoppingCart/Confirm")
    public String CompleteOrder(Model model, @ModelAttribute("pmh")PHIEUMUAHANG pmh) {
        PHIEUMUAHANG dh = shoppingCartService.GetSCDetail(pmh.getMaphieumuahang());
        if (dh == null) {return "redirect:/ShoppingCart/CreateDH";}

        //cap nhat trang thai don hang
        dh.setTinhtrangthanhtoan(pmh.getTinhtrangthanhtoan());
        dh.setTinhtrang("Xác Nhận");

        //cap nhat thong tin khach hang
        KHACH kyaku = qlkhachService.getKhachById(pmh.getKhach().getMakhachhang());
        kyaku.setTenkhachhang(pmh.getKhach().getTenkhachhang());
        kyaku.setDiachi(pmh.getKhach().getDiachi());
        kyaku.setSdt(pmh.getKhach().getSdt());
        kyaku.setEmail(pmh.getKhach().getEmail());

        /*THE the = cardService.FindThe(pmh.getKhach().getMakhachhang());
        if (the!=null) {
            int temp = (int) pmh.getDHTotal() /100;

            return "redirect:/Error/ErrorMe?mess=" + temp + " " + pmh.getDHTotal();
            //the.setDiemthe(the.getDiemthe()+temp);
            //cardService.UpdateCardPoint(the);
        }*/
        //return "redirect:/Error/ErrorMe?mess=" + "lỗi";
        try {

            qlkhachService.UpdateKhach(kyaku);
            shoppingCartService.Create(dh);

            //tich diem
            THE the = cardService.FindThe(pmh.getKhach().getMakhachhang());
            if (the!=null) {
                int temp = (int) pmh.getDHTotal() /100;
                the.setDiemthe(the.getDiemthe()+temp);
                cardService.UpdateCardPoint(the);
            }

            for(SACH sach : dh.getDsSach()) {
                if (sach == null) {return "redirect:/Error/ErrorMe?mess=" +"DSSACH rong";}

                sach.setSoluonghienco((int)sach.getSoluonghienco()-sach.getSoluongmua());
                //return "redirect:/Error/ErrorMe?mess=" +sach.getSoluonghienco();
                try {
                    qlsachService.Create(sach);
                } catch (Exception ex){
                    return "redirect:/Error/ErrorMe?mess=" + "Loi CN SL SACH" + ex.getMessage();
                }
            }

            httpSession.removeAttribute("donhang");
            return "redirect:/ShoppingCart/Index";
        } catch (Exception ex) {
            return "redirect:/Error/ErrorMe?mess=" + ex.getMessage();
        }



        //return "redirect:/ShoppingCart/Index";
    }
}
