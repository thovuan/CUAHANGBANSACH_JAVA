package com.cuahangbansach.cuahangbansach_java.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    @GetMapping("/Login/Index")
    public String Login() {
        return "Login/Index";
    }
}
