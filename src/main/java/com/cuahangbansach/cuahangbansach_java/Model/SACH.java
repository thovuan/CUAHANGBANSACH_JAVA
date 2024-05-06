package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
    private String masach;


    @Column(name="tensach")
    private String tensach;

    @Column(name="soluonghienco")
    private int soluonghienco;

    @Column(name="dacdiem")
    private String dacdiem;

    @Column(name="anhsanpham")
    private String anhsanpham;

    @Column(name="DVT")
    private String DVT;

    @Column(name="dongia")
    private int dongia;

    @JoinColumn(name="matheloai", referencedColumnName = "matheloai")
    @ManyToOne(fetch = FetchType.EAGER)
    private THELOAISACH theloaisach;



    @JoinColumn(name="manhanvien", referencedColumnName = "manhanvien")
    @ManyToOne(fetch = FetchType.EAGER)
    private NHANVIEN nhanvien;

    @JoinColumn(name="manxb", referencedColumnName = "manxb")
    @ManyToOne(fetch = FetchType.EAGER)
    private NXB nxb;

    @Transient
    private String tentheloai;

    @Transient
    private String tennxb;

    @Transient
    private String tennhanvien;

    @Transient
    @Min(value = 1, message = "Số lượng mua phải từ 1")
    private int soluongmua;

    @Transient
    private long thanhtien;
}
