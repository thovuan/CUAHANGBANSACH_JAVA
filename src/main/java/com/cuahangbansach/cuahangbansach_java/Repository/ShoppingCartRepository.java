package com.cuahangbansach.cuahangbansach_java.Repository;

import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<PHIEUMUAHANG, String> {

}
