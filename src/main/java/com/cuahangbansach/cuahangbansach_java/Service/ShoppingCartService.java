package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<PHIEUMUAHANG> GetList() {
        return shoppingCartRepository.findAll();
    }

    public PHIEUMUAHANG Create(PHIEUMUAHANG phieumuahang) {
        return shoppingCartRepository.save(phieumuahang);
    }
}
