package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;

@Entity
@Table(name ="SACH")
public class SACH {
    @Id
    @Column(name="masach")
    public String masach;

    @Column(name="tensach")
    public String tensach;

    @Column(name="soluonghienco")
    public int soluonghienco;

    @Column(name="dacdiem")
    public String dacdiem;

    @Column(name="anhsanpham")
    public String anhsanpham;

    @Column(name="DVT")
    public String DVT;

    @Column(name="dongia")
    public int dongia;

    @Column(name="matheloai")
    public String matheloai;

    @Column(name="manhanvien")
    public String manhanvien;

    @Column(name="manxb")
    public String manxb;

    @Transient
    public String tentheloai;

    @Transient
    public String tennxb;

    @Transient
    public String tennhanvien;
}
