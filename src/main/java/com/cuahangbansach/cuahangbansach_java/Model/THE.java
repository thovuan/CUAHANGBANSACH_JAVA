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
@Table(name = "THE")
public class THE {
    @Id
    @Column(name = "mathe")
    private String mathe;

    @Column(name = "diemthe")
    private int diemthe;

    @JoinColumn(name="makhachhang", referencedColumnName = "makhachhang")
    @ManyToOne(fetch = FetchType.LAZY)
    private KHACH makhachhang;
}
