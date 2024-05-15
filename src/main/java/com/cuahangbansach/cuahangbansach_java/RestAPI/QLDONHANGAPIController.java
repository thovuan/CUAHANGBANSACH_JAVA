package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Service.QLKHACHService;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qldonhang")
public class QLDONHANGAPIController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private QLKHACHService qlkhachService;

    @GetMapping("/index")
    public List<PHIEUMUAHANG> Index(Model model) {
        List<PHIEUMUAHANG> list =   shoppingCartService.GetList();
        for (PHIEUMUAHANG dh : list) {
            dh = shoppingCartService.GetSCDetail(dh.getMaphieumuahang());
        }
        return list;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PHIEUMUAHANG> find(@PathVariable String id) {
        return ResponseEntity.ok((PHIEUMUAHANG) shoppingCartService.GetList2(id));
    }


}
