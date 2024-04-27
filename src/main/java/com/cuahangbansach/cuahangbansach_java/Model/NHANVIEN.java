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
    public String manhanvien;

    @Column (name="tennhanvien")
    public String tennhanvien;

    @Column(name = "tendangnhap")
    public String tendangnhap;

    @Column(name = "matkhau")
    public String matkhau;
}
