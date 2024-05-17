package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Exception.ResourceNotFoundException;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tracking")
public class TrackingAPIController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/{id}&{email}")
    public ResponseEntity<PHIEUMUAHANG> TrackingCart(@PathVariable String id, @PathVariable String email) {
        PHIEUMUAHANG pmh = shoppingCartService.TrackingCart(id, email);

        if (pmh != null) {
            return ResponseEntity.ok(pmh);
        } else throw new ResourceNotFoundException("Data Not Found");
    }
}
