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
@IdClass(CHITIETDATHANG.class)
@Table(name = "CHITIETDATHANG")
public class CHITIETDATHANG {

    @Id
    @JoinColumn(name="maphieumuahang", referencedColumnName = "maphieumuahang")
    @ManyToOne(fetch = FetchType.LAZY)
    private PHIEUMUAHANG phieumuahang;

    @Id
    @JoinColumn(name="masach", referencedColumnName = "masach")
    @ManyToOne(fetch = FetchType.LAZY)
    private SACH sach;

    @Column(name = "soluongmua")
    @Min(value = 1, message = "Số lượng mua phải từ 1")
    private int soluongmua;

    @Column(name = "tinhtranggiao")
    private String tinhtranggiao;
}
