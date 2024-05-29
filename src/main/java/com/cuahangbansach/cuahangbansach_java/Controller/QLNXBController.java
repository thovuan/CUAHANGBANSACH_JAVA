package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Service.QLNXBService;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QLNXBController {
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StaffIdentityService staffIdentityService;

    @Autowired
    private QLNXBService qlnxbService;

    private Boolean CheckPermit() {
        NHANVIEN nv = (NHANVIEN) httpSession.getAttribute("nv");
        if (nv != null) return staffIdentityService.CheckPermit(nv.getManhanvien(), "CV02");
        return false;
    }

    @GetMapping("/QLNXB/Index")
    private String Index(Model model) {
        List<NXB> list = qlnxbService.GetList();
        model.addAttribute("NXB", list);
        return "/QLNXB/Index";
    }
}
