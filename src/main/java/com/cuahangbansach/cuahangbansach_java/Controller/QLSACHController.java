package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLSACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Service.QLNVService;
import com.cuahangbansach.cuahangbansach_java.Service.QLNXBService;
import com.cuahangbansach.cuahangbansach_java.Service.QLSACHService;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QLSACHController {
    @Autowired
    private QLSACHRepository qlsachRepository;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private QLNXBService qlnxbService;

    @Autowired
    private QLNVService qlnvService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/QLSACH/Index")
    public String QLSACHIndex(Model model, @RequestParam(name = "bookname", required = false) String bookname) {
        List<SACH> list;
        if (bookname != null) {
            list = qlsachService.getSACHByName(bookname);
        } else {
            list = qlsachService.getList();
        }
        model.addAttribute("SACHList", list);

        return "QLSACH/Index";
    }


    @GetMapping("/QLSACH/Create")
    public String AddSachGet(Model model) {
        List<THELOAISACH> list= qltheloaiService.GetAll();
        List<NXB> NXBlist = qlnxbService.GetList();

        model.addAttribute("sach", new SACH());
        model.addAttribute("TLList", list);
        model.addAttribute("NXBList", NXBlist);
        return "QLSACH/Create";
    }
    @PostMapping("/QLSACH/Save")
    private String SaveSACH(Model model, @ModelAttribute("sach")SACH sach) {
        SACH hon = new SACH();
        hon.setMasach(sach.getMasach());
        hon.setTensach(sach.getTensach());
        hon.setSoluonghienco(sach.getSoluonghienco());
        hon.setDacdiem(sach.getDacdiem());
        hon.setDongia(sach.getDongia());
        hon.setDVT(sach.getDVT());
        hon.setTheloaisach(qltheloaiService.GetCategoryById(sach.getTheloaisach().getMatheloai()));
        hon.setNxb(qlnxbService.GetNXBById(sach.getNxb().getManxb()));
        hon.setNhanvien(qlnvService.GetById("NV01"));

        try {
            qlsachService.Create(hon);
            //return "redirect:/QLSACH/Index";
            return "redirect:/QLSACH/Index";

        } catch (Exception ex) {
            //return "Lỗi";
            return "/Error/error";
        }
    }



    @GetMapping("/QLSACH/Edit/{id}")
    public String QLSACHEdit(Model model, @PathVariable("id") String id) {
        List<THELOAISACH> list= qltheloaiService.GetAll();
        List<NXB> NXBlist = qlnxbService.GetList();
        SACH sach = qlsachService.GetSachById(id);
        if (sach != null) {
            SACH hon = new SACH();
            //hon = sach;
            hon.setMasach(sach.getMasach());
            hon.setTensach(sach.getTensach());
            hon.setSoluonghienco(sach.getSoluonghienco());
            hon.setDacdiem(sach.getDacdiem());
            hon.setDongia(sach.getDongia());
            hon.setDVT(sach.getDVT());
            hon.setTheloaisach(qltheloaiService.GetCategoryById(sach.getTheloaisach().getMatheloai()));
            hon.setNxb(qlnxbService.GetNXBById(sach.getNxb().getManxb()));
//        hon.setNhanvien(sach.getNhanvien());
            model.addAttribute("sach", hon);
            model.addAttribute("TLList", list);
            model.addAttribute("NXBList", NXBlist);
            return "QLSACH/Edit";
        }
        return "Loi";
    }

//    @PostMapping("/QLSACH/Edit/{id}")
//    public String Edit (Model model, @ModelAttribute("sach")SACH sach, @PathVariable("id") String id) {
//        SACH hon = qlsachService.GetSachById(id);
//        if (hon == null) return "Can't Find Book: " + id;
//
//        hon.setTensach(sach.getTensach());
//        hon.setSoluonghienco(sach.getSoluonghienco());
//        hon.setDacdiem(sach.getDacdiem());
//        hon.setDongia(sach.getDongia());
//        hon.setDVT(sach.getDVT());
//        hon.setTheloaisach(qltheloaiService.GetCategoryById(sach.getTheloaisach().getMatheloai()));
//        hon.setNxb(qlnxbService.GetNXBById(sach.getNxb().getManxb()));
//        hon.setNhanvien(qlnvService.GetById("NV01"));
//
//        try {
//            qlsachService.Update(hon);
//            return "redirect:/QLSACH/Index";
//
//
//        } catch (Exception ex) {
//            return "Lỗi";
//        }
//
//
//    }

    @GetMapping("/QLSACH/Details/{id}")
    public String Details(Model model, @PathVariable("id") String id) {
        SACH sach = qlsachService.GetSachById(id);
        if (sach == null) return "Can't Find Book: " + id;
        model.addAttribute("SACH", sach);
        return "QLSACH/Details";
    }

    @GetMapping("/QLSACH/Delete/{id}")
    public String Delete(SACH sach, @PathVariable("id")String id) {

        SACH hon = qlsachService.GetSachById(id);
        if (hon == null) return "Can't Find Book: " + id;

        try {
            qlsachService.Delete(id);
            return "redirect:/QLSACH/Index";
        } catch(Exception ex) {
            return "Can't Delete Sach: " + id;
        }


    }


}
