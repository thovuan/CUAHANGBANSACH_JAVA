package com.cuahangbansach.cuahangbansach_java.Controller;
import java.util.List;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Service.QLKHACHService;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.cuahangbansach.cuahangbansach_java.Repository.QLKHACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class QLKHACHController {
    @Autowired private QLKHACHRepository qlkhachRepository;

    @Autowired private QLKHACHService qlkhachService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StaffIdentityService staffIdentityService;

    private Boolean CheckPermit() {
        NHANVIEN nv = (NHANVIEN) httpSession.getAttribute("nv");
        if (nv != null) return staffIdentityService.CheckPermit(nv.getManhanvien(), "CV01");
        return false;
    }

    @GetMapping("/QLTK/QLKHACH/Index")
    public String index(Model model, @RequestParam(name = "guestname", required = false) String guestname) {

        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        List<KHACH> list;
        if (guestname != null) {
            list = qlkhachService.GetByName(guestname);
            model.addAttribute("KhachList", list);
        } else {
            list = qlkhachRepository.GetList();
            model.addAttribute("KhachList", list);
        }

        return "/QLTK/QLKHACH/Index";
    }
    @GetMapping("/QLTK/QLKHACH/Detail/{id}")
    public String Detail(Model model, @PathVariable("id") String id) {

        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        KHACH khach = qlkhachService.getKhachById(id);
        if (khach != null) {
            model.addAttribute("TTKHACH", khach);
            return "/QLTK/QLKHACH/Detail";
        }
        return "Loi khong tim thay";
    }
}
