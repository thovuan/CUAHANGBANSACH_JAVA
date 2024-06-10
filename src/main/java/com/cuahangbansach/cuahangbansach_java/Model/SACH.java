package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Table(name ="SACH")
public class SACH {
    @Id
    @Column(name="masach")
    private String masach;

    @NotNull(message = "Truong ten sach khong duoc de trong")
    @Column(name="tensach")
    private String tensach;


    @Min(value = 0, message = "So luong sach khong the nao am dc")
    @Column(name="soluonghienco")
    private int soluonghienco;


    @Column(name="dacdiem")
    private String dacdiem;

    @Column(name="anhsanpham")
    private String anhsanpham;

    @NotNull(message = "Truong don vi tinh khong duoc de trong")
    @Column(name="DVT")
    private String DVT;

    @Min(value = 0, message = "0 dong tro len cho gia tri sach")
    @Column(name="dongia")
    private int dongia;

    @JoinColumn(name="matheloai", referencedColumnName = "matheloai")
    @ManyToOne(fetch = FetchType.EAGER)
    private THELOAISACH matheloai;


    @JoinColumn(name="manhanvien", referencedColumnName = "manhanvien")
    @ManyToOne(fetch = FetchType.EAGER)
    private NHANVIEN manhanvien;

    @JoinColumn(name="manxb", referencedColumnName = "manxb")
    @ManyToOne(fetch = FetchType.EAGER)
    private NXB manxb;

    //@OneToMany(mappedBy = "masach", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Transient
    private List<BookComment> bookComments;

    @Transient
    private String tentheloai;

    @Transient
    private String tennxb;

    @Transient
    private String tennhanvien;

    @Transient
    private int soluongmua;

    @Transient
    private long thanhtien;
}
