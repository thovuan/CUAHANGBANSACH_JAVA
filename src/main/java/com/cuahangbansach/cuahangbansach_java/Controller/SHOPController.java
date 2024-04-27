package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Service.QLNXBService;
import com.cuahangbansach.cuahangbansach_java.Service.QLSACHService;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SHOPController {

    @Autowired
    private QLNXBService qlnxbService;

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private QLSACHService qlsachService;

    @GetMapping("/SHOP/Index")
    public String Index(Model model, @RequestParam(required = false) String MaTL, @RequestParam(required = false) String MaNXB) {
        //take all book list
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


}
