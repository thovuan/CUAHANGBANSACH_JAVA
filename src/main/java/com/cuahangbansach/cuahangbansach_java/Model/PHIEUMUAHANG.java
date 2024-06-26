package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Table(name = "PHIEUMUAHANG")
public class PHIEUMUAHANG {
    @Id
    @Column(name = "maphieumuahang")
    private String maphieumuahang;

    @Column(name = "ngaylap")
    private LocalDateTime ngaylap;


    @Column(name = "tinhtrangthanhtoan")
    private String tinhtrangthanhtoan;

    @Column(name = "tinhtrang")
    private String tinhtrang;

    @JoinColumn(name="makhachhang", referencedColumnName = "makhachhang")
    @ManyToOne(fetch = FetchType.EAGER)
    private KHACH khach;

    @Column(name = "cost")
    private long DHTotal;

    @Transient
    private Long allofcost;

    @Transient
    private long costpromo;

    @Transient
    public List<SACH> dsSach;

    @OneToMany(mappedBy = "maphieumuahang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CHITIETDATHANG> chitietDH;
}
