package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
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

    @JoinColumn(name="matheloai", referencedColumnName = "matheloai")
    @ManyToOne(fetch = FetchType.LAZY)
    public THELOAISACH theloaisach;



    @JoinColumn(name="manhanvien", referencedColumnName = "manhanvien")
    @ManyToOne(fetch = FetchType.LAZY)
    public NHANVIEN nhanvien;

    @JoinColumn(name="manxb", referencedColumnName = "manxb")
    @ManyToOne(fetch = FetchType.LAZY)
    public NXB nxb;

    @Transient
    public String tentheloai;

    @Transient
    public String tennxb;

    @Transient
    public String tennhanvien;

    @Transient
    private int soluongmua;

    @Transient
    private long thanhtien;
}
