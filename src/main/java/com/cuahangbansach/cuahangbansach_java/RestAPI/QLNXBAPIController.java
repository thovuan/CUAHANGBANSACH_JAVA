package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Repository.QLNXBRepository;
import com.cuahangbansach.cuahangbansach_java.Service.QLNXBService;
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
    public ResponseEntity<NXB> Detail(@PathVariable String id) {
        NXB nxb = qlnxbRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book's Id Not Found: " + id));
        return ResponseEntity.ok(nxb);
    }

    @PostMapping
    public ResponseEntity<String> Add(@RequestBody  NXB nxb) {
        try {
            qlnxbRepository.save(nxb);
            return ResponseEntity.ok("Added Successfully");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> Update(@RequestBody  NXB nxb, @PathVariable String id) {
        NXB Tnxb = qlnxbService.GetNXBById(id);
        if (Tnxb != null) {
            Tnxb.setTennxb(nxb.getTennxb());

            try {
                qlnxbService.Save(Tnxb);
                return ResponseEntity.ok("Updated Successfully");
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("No such Book Id: " + id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@RequestBody NXB nxb,@PathVariable String id) {
        NXB Tnxb = qlnxbService.GetNXBById(id);
        if (Tnxb != null) {

            try {
                qlnxbService.Delete(Tnxb);
                return ResponseEntity.ok("Deleted Successfully");
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("No such Book Id: " + id);
    }
}
