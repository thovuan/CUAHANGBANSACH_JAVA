package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Service.QLCVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QLCHUCVUController {

    @Autowired
    private QLCVService qlcvService;

    @GetMapping("/QLTK/QLTKCT/QLCV/Index")
    public String Index(Model model) {
        model.addAttribute("DSCV", qlcvService.getList());
        return "/QLTK/QLTKCT/QLCV/Index";
    }
}
