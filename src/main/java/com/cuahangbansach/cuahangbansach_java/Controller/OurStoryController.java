package com.cuahangbansach.cuahangbansach_java.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OurStoryController {

    @GetMapping("/About/OurStory")
    public String OS() {
        return "/About/OurStory";
    }
}
