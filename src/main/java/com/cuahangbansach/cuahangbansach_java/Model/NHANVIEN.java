package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name="NHANVIEN")
public class NHANVIEN {

    @Id
    @Column (name = "manhanvien")
    private String manhanvien;

    @Column (name="tennhanvien")
    private String tennhanvien;

    @Column(name = "tendangnhap")
    private String tendangnhap;

    @Column(name = "matkhau")
    private String matkhau;
}
