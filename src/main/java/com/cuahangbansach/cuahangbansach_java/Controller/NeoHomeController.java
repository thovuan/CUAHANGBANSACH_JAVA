package com.cuahangbansach.cuahangbansach_java.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NeoHomeController {
    @GetMapping("/NeoHome/Index")
    public String Index() {
        return "/NeoHome/Index";
    }
}
