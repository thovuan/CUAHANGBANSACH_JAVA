package com.cuahangbansach.cuahangbansach_java.Model;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
public class PHIEUMUAHANG_KHACH {
    //phieumuahang
    private String maphieumuahang;
    private LocalDateTime ngaylap;
    private String tinhtrangthanhtoan;
    private String tinhtrang;

    //khach
    private String makhachhang;
    private String tenkhachhang;
    private String diachi;
    private String sdt;

    @Email(message = "Invalid Email")
    private String email;
    private String avatar;
    private String tendangnhap;
    private String matkhau;

    private PHIEUMUAHANG phieumuahang;
    private KHACH khach;
}
