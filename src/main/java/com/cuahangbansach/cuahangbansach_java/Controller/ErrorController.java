package com.cuahangbansach.cuahangbansach_java.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {
    @GetMapping("/Error/ErrorMe")
    public ResponseEntity<String> ErrorMe (@RequestParam("mess") String mess) {
        return ResponseEntity.badRequest().body(mess);
    }
}
