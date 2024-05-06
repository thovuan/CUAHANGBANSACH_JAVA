package com.cuahangbansach.cuahangbansach_java.Controller;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QLTHELOAIController {

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @GetMapping("/QLTHELOAI/Index")
    public String Index (Model model, @RequestParam(name = "tlname", required = false)String tl) {
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
    @GetMapping("/QLTHELOAI/ListAPI")
    public List<THELOAISACH> GetList() {
        return qltheloaiService.GetAll();
    }

    @GetMapping("/QLTHELOAI/Detail/{id}")
    public String Detail(Model model, @PathVariable String id) {
        THELOAISACH tls = qltheloaiService.GetCategoryById(id);
        if (tls != null) {
            model.addAttribute("TL", tls);
            return "QLTHELOAI/Detail";
        }
        return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";
    }
}
