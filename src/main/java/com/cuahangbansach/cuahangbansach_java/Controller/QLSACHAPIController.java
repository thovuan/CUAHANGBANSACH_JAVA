package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLSACHRepository;
import com.cuahangbansach.cuahangbansach_java.Service.QLNVService;
import com.cuahangbansach.cuahangbansach_java.Service.QLNXBService;
import com.cuahangbansach.cuahangbansach_java.Service.QLSACHService;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/QLSACH")
public class QLSACHAPIController {
    @Autowired
    private QLSACHRepository qlsachRepository;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private QLNXBService qlnxbService;

    @Autowired
    private QLNVService qlnvService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public List<SACH> getSACHList() {
        return qlsachService.getList();
    }

    @PostMapping("/Create")
    public SACH Add(@RequestBody SACH sach) {
        return qlsachService.Create(sach);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SACH> getSachById(@PathVariable("id") String id) {
        SACH sach = qlsachRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book's Id Not Found: " + id));
        return ResponseEntity.ok(sach);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SACH> Update(@PathVariable String id, @RequestBody SACH sach) {
        SACH hon = qlsachRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book's Id Not Found: " + id));
        hon.setTensach(sach.getTensach());
        hon.setSoluonghienco(sach.getSoluonghienco());
        hon.setDacdiem(sach.getDacdiem());
        hon.setDongia(sach.getDongia());
        hon.setDVT(sach.getDVT());
        hon.setTheloaisach(sach.getTheloaisach());
        hon.setNxb(sach.getNxb());

        SACH book = qlsachService.Create(hon);
        return ResponseEntity.ok(book);
    }
}
