package com.cuahangbansach.cuahangbansach_java.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    @NonNull()
    @Column(name = "tenkhachhang")
    public String tenkhachhang;

    @Column(name = "diachi")
    public String diachi;

    @Column(name = "sdt")
    @Size(min = 10, max = 11, message = "The phone number is only 10 - 11 numbers")
    public String sdt;

    @Email(message = "Invalid Email")
    @Column(name = "email")
    public String email;

    @Column(name = "avatar")
    public String avatar;

    @Column(name = "tendangnhap")
    public String tendangnhap;

    @Column(name = "matkhau")
    public String matkhau;

}
