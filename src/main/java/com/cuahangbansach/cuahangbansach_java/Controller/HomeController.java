package com.cuahangbansach.cuahangbansach_java.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {
    @GetMapping("/Home/index")
    public String homepage() {
        return "/Home/index";  // Trả về trang index.html
    }


}
