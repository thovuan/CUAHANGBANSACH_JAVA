package com.cuahangbansach.cuahangbansach_java.Controller;
import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class QLTHELOAIController {

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StaffIdentityService staffIdentityService;

    private Boolean CheckPermit() {
        NHANVIEN nv = (NHANVIEN) httpSession.getAttribute("nv");
        if (nv != null) return staffIdentityService.CheckPermit(nv.getManhanvien(), "CV02");
        return false;
    }

    @GetMapping("/QLTHELOAI/Index")
    public String Index (Model model, @RequestParam(name = "tlname", required = false)String tl) {
        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        List<THELOAISACH> list;
        if (tl != null) {
            list = qltheloaiService.GetCategoryByName(tl);
            model.addAttribute("TLList", list);

        } else {
            list = qltheloaiService.GetAll();
            model.addAttribute("TLList", list);
        }

        return "QLTHELOAI/Index";
    }

    @GetMapping("/QLTHELOAI/Detail/{id}")
    public String Detail(Model model, @PathVariable String id) {
        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        THELOAISACH tls = qltheloaiService.GetCategoryById(id);
        if (tls != null) {
            model.addAttribute("TL", tls);
            return "QLTHELOAI/Detail";
        }
        return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";
    }
}
