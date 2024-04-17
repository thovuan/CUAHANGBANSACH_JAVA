package com.cuahangbansach.cuahangbansach_java.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.lang.NonNull;


@Setter
@Getter
@Entity
@Table(name = "KHACH")
public class KHACH {
    @Id
    @Column(name = "makhachhang")
    public String makhachhang;

    @Column(name = "tenkhachhang")
    public String tenkhachhang;

    @Column(name = "diachi")
    public String diachi;

    @Column(name = "sdt")

    public String sdt;

    @Column(name = "email")
    public String email;

    @Column(name = "avatar")
    public String avatar;

    @Column(name = "tendangnhap")
    public String tendangnhap;

    @Column(name = "matkhau")
    public String matkhau;

}
