package com.cuahangbansach.cuahangbansach_java.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homepage() {
        return "Home/index";  // Trả về trang index.html
    }


}
