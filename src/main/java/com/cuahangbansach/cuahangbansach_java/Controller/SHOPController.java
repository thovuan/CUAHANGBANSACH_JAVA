package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.*;
import com.cuahangbansach.cuahangbansach_java.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SHOPController {

    @Autowired
    private QLNXBService qlnxbService;

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private QLKHACHService qlkhachService;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/SHOP/Index")
    public String Index(Model model, @RequestParam(required = false) String MaTL, @RequestParam(required = false) String MaNXB) {
        //take all book list
        PHIEUMUAHANG dh = shoppingCartService.GetDH("KH01");
        httpSession.setAttribute("donhang", dh);
        //return "redirect:/ErrorMe?mess=" + dh.getMaphieumuahang();
        List<SACH> list;
        if (MaTL != null) {list = qlsachService.getSACHbyMaTL(MaTL);}
        else if (MaNXB != null) {list = qlsachService.getSACHbyMaNXB(MaNXB);}
        else list = qlsachService.getList();
        model.addAttribute("list", list);

        //take the list of Category
        List<THELOAISACH> listTL = qltheloaiService.Read();
        model.addAttribute("listTL", listTL);

        //take the list of NXB
        List<NXB> listNXB = qlnxbService.Read();
        model.addAttribute("listNXB", listNXB);
        return "SHOP/Index";

    }

    /*@GetMapping("/SHOP/Index_TheoTL/{MaTL}")
    public String Index_TheoTL(Model model, @PathVariable String MaTL) {
        List<SACH> hon = qlsachService.getSACHbyMaTL(MaTL);
        model.addAttribute("list", hon);
        return "SHOP/Index";
    }

    @GetMapping("/SHOP/Index_TheoNXB/{MaNXB}")
    public String Index_TheoNXB(Model model, @PathVariable String MaNXB) {
        List<SACH> hon = qlsachService.getSACHbyMaNXB(MaNXB);
        model.addAttribute("list", hon);
        return "SHOP/Index";
    }*/
    @GetMapping("/SHOP/Detail/{id}")
    public String BookDetail(Model model, @PathVariable String id) {
        SACH hon = qlsachService.GetSachById(id);
        if (hon == null) return "Can't Find Book: " + id;
        model.addAttribute("SACH", hon);
        return "SHOP/Detail";
    }

    @GetMapping("/SHOP/Buy/{id}")
    public String Buy(Model model, @PathVariable String id) {
        SACH hon = qlsachService.GetSachById(id);
        if (hon == null) return "Can't Find Book: " + id;
        model.addAttribute("SACH", hon);
        return "SHOP/Buy";
    }

    @PostMapping("/SHOP/AddToCart")
    public String AddToCart(Model model, @ModelAttribute("SACH") SACH sach) {
        SACH hon = qlsachService.GetSachById(sach.getMasach());
        PHIEUMUAHANG pmh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");

        //tao them don hang
        if (pmh==null) {
            //return "redirect:/ShoppingCart/CreateDH";
            return "redirect:/ShoppingCart/CreateDH";
        }

        //tim kiem sach trong chi tiet don hang de cong don so luong
        CHITIETDATHANG dh = shoppingCartService.GetCHHById(pmh.getMaphieumuahang(),sach.getMasach());
        int sl = 0;
        if (dh==null) sl = sach.getSoluongmua();
        else sl = dh.getSoluongmua() + sach.getSoluongmua();
        //return "redirect:/ErrorMe?mess=" + sl;

        //neu soluong mua vuot qua so luong hien co -> bao loi
        if (hon.getSoluonghienco() < sl) {
            return "redirect:/Error/ErrorMe?mess=" + "So luong mua khong vuot qua so luong hien tai";
        }

        //tien hanh them sach vao
        CHITIETDATHANG ct = new CHITIETDATHANG();
        ct.setPhieumuahang(shoppingCartService.GetPMHById(pmh.getMaphieumuahang()));
        ct.setSach(hon);
        ct.setSoluongmua(sl);
        ct.setTinhtranggiao("Chưa giao");
        //model.addAttribute("Error", "Số lượng mua vượt qua số lượng hiện c");

        try {
            shoppingCartService.Add(ct);
            //return "redirect:/SHOP/Index";
            return "redirect:/SHOP/Index";

        } catch (Exception ex) {
            return "redirect:/Error/ErrorMe?mess=" + ex.getMessage();
        }


    }





}
