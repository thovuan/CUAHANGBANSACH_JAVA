package com.cuahangbansach.cuahangbansach_java.Controller;


import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.*;
import com.cuahangbansach.cuahangbansach_java.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private QLKHACHService qlkhachService;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private CardService cardService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private EmailSenderService mailSenderService;



    @GetMapping("/ShoppingCart/Index")
    public String Index(Model model, @RequestParam(name = "dhid", required = false)String pmh) {
        KHACH kyaku = (KHACH) httpSession.getAttribute("guest");
        List<PHIEUMUAHANG> list;
        if (pmh != null) {
            list = shoppingCartService.GetList2(pmh);
            model.addAttribute("SCL", list);
        } else {
            if (kyaku != null) {
                list = shoppingCartService.GetListByGuestId(kyaku.getMakhachhang());
                model.addAttribute("SCL", list);
            }
            else model.addAttribute("SCL", null);
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
        KHACH kyaku = (KHACH) httpSession.getAttribute("guest");
        PHIEUMUAHANG dh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");
        if (kyaku == null || dh == null) {

            httpSession.setAttribute("donhang", null);
        } else {
            //dh = shoppingCartService.GetDH(kyaku.getMakhachhang());
            httpSession.setAttribute("donhang", dh);

        }

        model.addAttribute("pmh", dh);
        return "/ShoppingCart/MyCart";
    }

    @GetMapping("/ShoppingCart/Create")
    public String Create (Model model) {
        KHACH kyaku = (KHACH) httpSession.getAttribute("guest");
        PHIEUMUAHANG dh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");

        if (kyaku==null) {
            LocalDateTime dt = LocalDateTime.now();
            KHACH khach = new KHACH();
            khach.setMakhachhang("KH" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
            khach.setTenkhachhang("Guest" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
            khach.setSdt("0123456789");
            khach.setDiachi("Unk");

            try {
                qlkhachService.UpdateKhach(khach);
                httpSession.setAttribute("guest", khach);
                kyaku = khach;

            } catch(Exception ex) {
                return "redirect:/Error/ErrorMe?mess=" + "Không the tạo đơn hàng";
            }
        }

        if (dh != null) return "redirect:/Error/ErrorMe?mess=" + "Không the tạo đơn hàng";
        LocalDateTime dt = LocalDateTime.now();
        PHIEUMUAHANG pmh = new PHIEUMUAHANG();
        pmh.setMaphieumuahang("DH" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
        pmh.setNgaylap(dt);
        pmh.setTinhtrangthanhtoan("Chưa thanh toán");
        pmh.setTinhtrang("Chưa xác nhận");
        pmh.setKhach(qlkhachService.getKhachById(kyaku.getMakhachhang()));
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
        KHACH kyaku = (KHACH) httpSession.getAttribute("guest");
        LocalDateTime dt = LocalDateTime.now();
        PHIEUMUAHANG pmh = new PHIEUMUAHANG();
        pmh.setMaphieumuahang("DH" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
        pmh.setNgaylap(dt);
        pmh.setTinhtrangthanhtoan("Chưa thanh toán");
        pmh.setTinhtrang("Chưa xác nhận");
        pmh.setKhach(qlkhachService.getKhachById(kyaku.getMakhachhang()));
        try {
            shoppingCartService.Create(pmh);
            httpSession.setAttribute("donhang", pmh);
            return "redirect:/SHOP/AddToCart";

        } catch (Exception ex) {
            return "Loi";
        }

    }

    @GetMapping("/ShoppingCart/EditSLBook/{id}")
    public String EditSLBook(Model model, @RequestParam(name = "soluongmua", required = false)CHITIETDATHANG soluongmua, @PathVariable String id) {
        PHIEUMUAHANG pmh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");
        CHITIETDATHANG ctdh = shoppingCartService.GetCHHById(pmh.getMaphieumuahang(), id);
        if (ctdh!=null) {
//            CHITIETDATHANG ct = new CHITIETDATHANG();
//            ct.setPhieumuahang(ctdh.getPhieumuahang());
//            ct.setSach(ctdh.getSach());
//            ct.setSoluongmua(ctdh.getSoluongmua());
//            ct.setTinhtranggiao(ctdh.getTinhtranggiao());
            model.addAttribute("SACH", ctdh);
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
        CHITIETDATHANG ct = shoppingCartService.GetCHHById(pmh.getMaphieumuahang(), sach.getSach().getMasach());
        //ct.setPhieumuahang(shoppingCartService.GetPMHById(pmh.getMaphieumuahang()));
        //ct.setSach(hon);
        ct.setSoluongmua(sach.getSoluongmua());
        //ct.setTinhtranggiao("Chưa giao");
        //model.addAttribute("Error", "Số lượng mua vượt qua số lượng hiện c");

        try {
            shoppingCartService.UpdateCTDH(ct);
            //return "redirect:/SHOP/Index";
            return "redirect:/ShoppingCart/MyCart";

        } catch (Exception ex) {
//            return "redirect:/Error/ErrorMe?mess=" + ct.getPhieumuahang().getMaphieumuahang() + '\n' + ct.getSach().getMasach()
//            + '\n' + ct.getSoluongmua() + '\n' +ct.toString();
            return "redirect:/Error/ErrorMe?mess=" + ex.getMessage();
        }
    }

    @GetMapping("/ShoppingCart/Delete/{id}")
    public String DeleteDH(@PathVariable String id, Model model) {
        PHIEUMUAHANG pmh = shoppingCartService.GetPMHById(id);
        if (pmh==null) throw new ResourceNotFoundException("Resource not found: " + id);
        try {
            shoppingCartService.Delete(pmh);
            return "redirect:/ShoppingCart/Index";
        } catch(Exception ex) {
            return "/ShoppingCart/Index";
        }
    }

    @GetMapping("/ShoppingCart/Confirm/{id}")
    public String Confirm(Model model, @PathVariable String id) {
        PHIEUMUAHANG pmh = shoppingCartService.GetSCDetail(id);
        if (pmh == null) {return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";}
        List<KHUYENMAI> promo = promotionService.GetSuggetedPromotion(pmh.getDHTotal());
        model.addAttribute("pmh", pmh);
        model.addAttribute("promo", promo);
        return "ShoppingCart/Confirm";
    }

//    @PutMapping("/ShoppingCart/Confirm")
//    public String UsePromo(Model model) {
//        return "redirect:/ShoppingCart/Confirm";
//    }

    @PostMapping("/ShoppingCart/Confirm")
    public String CompleteOrder(Model model,@ModelAttribute("promo")KHUYENMAI promo, @ModelAttribute("pmh")PHIEUMUAHANG pmh) {
        PHIEUMUAHANG dh = shoppingCartService.GetSCDetail(pmh.getMaphieumuahang());
        if (dh == null) {return "redirect:/ShoppingCart/CreateDH";}

        KHUYENMAI pro = new KHUYENMAI();
        if (promo.getCode() != "0" ) {
            pro = promotionService.getById(promo.getCode());
            pro.setQuantity(pro.getQuantity()-1);

            int temp = (int) dh.getDHTotal()*(1-pro.getDiscount()/100);
            dh.setDHTotal(temp);
        }

        //cap nhat trang thai don hang
        dh.setTinhtrangthanhtoan(pmh.getTinhtrangthanhtoan());
        dh.setTinhtrang("Xác Nhận");


        //cap nhat thong tin khach hang
        KHACH kyaku = qlkhachService.getKhachById(pmh.getKhach().getMakhachhang());
        kyaku.setTenkhachhang(pmh.getKhach().getTenkhachhang());
        kyaku.setDiachi(pmh.getKhach().getDiachi());
        kyaku.setSdt(pmh.getKhach().getSdt());
        kyaku.setEmail(pmh.getKhach().getEmail());

        try {

            qlkhachService.UpdateKhach(kyaku);
            shoppingCartService.Create(dh);
            promotionService.Save(pro);

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

            mailSenderService.sendCartConfirmMail(kyaku.getEmail(), "Xác nhận đơn hàng #"+dh.getMaphieumuahang(), dh, kyaku, "ShoppingCart/OrderCompleteMail" );



            //tich diem
            THE the = cardService.FindThe(pmh.getKhach().getMakhachhang());
            if (the!=null) {
                int temp = (int) pmh.getDHTotal() /100;
                the.setDiemthe(the.getDiemthe()+temp);
                cardService.UpdateCardPoint(the);
            }

            httpSession.removeAttribute("donhang");
            return "redirect:/ShoppingCart/OrderComplete";
        } catch (Exception ex) {
            return "redirect:/Error/ErrorMe?mess=" + ex.getMessage();
        }

    }

    @GetMapping("/ShoppingCart/OrderComplete")
    public String OrderComplete(Model model) {
        return "ShoppingCart/OrderComplete";
    }

    @GetMapping("/ShoppingCart/DeleteBook/{id}")
    public String DeleteBook(@PathVariable String id, Model model) {
        PHIEUMUAHANG dh = (PHIEUMUAHANG) httpSession.getAttribute("donhang");
        //SACH hon = qlsachService.GetSachById(id);
        if (dh != null) {
            CHITIETDATHANG ct = shoppingCartService.GetCHHById(dh.getMaphieumuahang(), id);
            try {
                shoppingCartService.DeleteBook(ct);
                return "redirect:/ShoppingCart/MyCart";
            } catch (Exception ex) {
                return "redirect:/Error/ErrorMe?mess=" + ex.getMessage();
            }

        } else {
            //dh = shoppingCartService.GetDH(kyaku.getMakhachhang());

            return "redirect:/Error/ErrorMe?mess=" + "Not found";
        }



    }

}
