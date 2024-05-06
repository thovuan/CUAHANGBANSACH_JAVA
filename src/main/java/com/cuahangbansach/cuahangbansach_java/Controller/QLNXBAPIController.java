package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLNXBRepository;
import com.cuahangbansach.cuahangbansach_java.Service.QLNXBService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/QLNXB")
public class QLNXBAPIController {

    @Autowired
    private QLNXBRepository qlnxbRepository;

    @Autowired
    private QLNXBService qlnxbService;

    @GetMapping
    public List<NXB> GetList() {
        return qlnxbService.GetList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NXB> GetById(@PathVariable String id) {
        NXB nxb = qlnxbRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book's Id Not Found: " + id));
        return ResponseEntity.ok(nxb);
    }
}
