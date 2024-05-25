package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Service.QLNVService;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QLNHANVIENController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private QLNVService qlnvService;

    @Autowired
    private StaffIdentityService staffIdentityService;

    private Boolean CheckPermit() {
        NHANVIEN nv = (NHANVIEN) httpSession.getAttribute("nv");
        if (nv != null) return staffIdentityService.CheckPermit(nv.getManhanvien(), "CV01");
        return false;
    }

    @GetMapping("/QLTK/QLTKCT/QLNV/Index")
    public String Index(Model model, @RequestParam(name = "staffname", required = false) String staffname) {
        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        List<NHANVIEN> list;
        if (staffname != null) {
            list = qlnvService.getName(staffname);
        }
        else {
            list = qlnvService.getList();
        }


        model.addAttribute("nv", list);
        return "/QLTK/QLTKCT/QLNV/Index";
    }

    @GetMapping("/QLTK/QLTKCT/QLNV/Detail/{id}")
    public String Detail (Model model, @PathVariable String id){
        NHANVIEN nv = qlnvService.GetById(id);
        model.addAttribute("nv", nv);
        return "/QLTK/QLTKCT/QLNV/Detail";
    }
}
