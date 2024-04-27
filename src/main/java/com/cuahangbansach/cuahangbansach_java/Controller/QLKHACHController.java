package com.cuahangbansach.cuahangbansach_java.Controller;
import java.util.List;

import com.cuahangbansach.cuahangbansach_java.Service.QLKHACHService;
import org.springframework.ui.Model;
import com.cuahangbansach.cuahangbansach_java.Repository.QLKHACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QLKHACHController {
    @Autowired private QLKHACHRepository qlkhachRepository;

    @Autowired private QLKHACHService qlkhachService;

    @GetMapping("/QLKHACH/Index")
    public String index(Model model) {
        List<KHACH> list = qlkhachRepository.GetList();
        model.addAttribute("KhachList", list);
        return "QLKHACH/Index";
    }
    @GetMapping("/QLKHACH/Detail/{id}")
    public String Detail(Model model, @PathVariable("id") String id) {
        KHACH khach = qlkhachService.getKhachById(id);
        if (khach != null) {
            model.addAttribute("TTKHACH", khach);
            return "QLKHACH/Detail";
        }
        return "Loi khong tim thay";
    }
}
