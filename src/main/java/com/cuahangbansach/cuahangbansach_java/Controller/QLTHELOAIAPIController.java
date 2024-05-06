package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLTHELOAIRepository;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/QLTHELOAI")
public class QLTHELOAIAPIController {
    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private QLTHELOAIRepository qltheloaiRepository;

    @GetMapping
    public List<THELOAISACH> getList() {
        return qltheloaiService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<THELOAISACH> Detail(@PathVariable String id) {
        THELOAISACH tls = qltheloaiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category's Id Not Found: " + id));
        return ResponseEntity.ok(tls);
    }
}
