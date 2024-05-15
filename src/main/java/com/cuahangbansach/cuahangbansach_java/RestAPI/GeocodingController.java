package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testmap")
public class GeocodingController {
    @Autowired
    private GeocodingService geocodingService;

    @GetMapping("/GC")
    public ResponseEntity<String> getCoordinates(String address) {
        return ResponseEntity.ok(geocodingService.getCoordinates(address));
    }
}
