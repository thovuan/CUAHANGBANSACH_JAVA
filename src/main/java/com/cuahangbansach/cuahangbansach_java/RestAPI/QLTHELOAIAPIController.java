package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLTHELOAIRepository;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/Add")
    public ResponseEntity<String> Add(@RequestBody THELOAISACH tls) {
        try {
            qltheloaiService.Save(tls);
            return ResponseEntity.ok("Added Successfully");

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> Update(@PathVariable String id, @RequestBody THELOAISACH tls) {
        THELOAISACH cate = qltheloaiService.GetCategoryById(id);
        if (cate != null) {
            cate.setTentheloai(tls.getTentheloai());

            try {
                qltheloaiService.Save(cate);
                return ResponseEntity.ok("Add Category 完了");
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.toString());
            }
        }
        return ResponseEntity.badRequest().body("Not Found: " + id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable String id, @RequestBody THELOAISACH tls) {
        THELOAISACH cate = qltheloaiService.GetCategoryById(id);
        if (cate != null) {

            try {
                qltheloaiService.Delete(cate);
                return ResponseEntity.ok("Delete Category 完了");
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.toString());
            }
        }
        return ResponseEntity.badRequest().body("Not Found: " + id);
    }
}
