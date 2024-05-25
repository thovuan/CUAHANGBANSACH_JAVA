package com.cuahangbansach.cuahangbansach_java.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QLCHUCVUController {

    @GetMapping("/QLTK/QLTKCT/QLCV/Index")
    public String Index() {
        return "/QLTK/QLTKCT/QLCV/Index";
    }
}
