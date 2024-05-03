package com.cuahangbansach.cuahangbansach_java.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    private String makhachhang;

    @NonNull()
    @Column(name = "tenkhachhang")
    private String tenkhachhang;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "sdt")
    @Size(min = 10, max = 11, message = "The phone number is only 10 - 11 numbers")
    private String sdt;

    @Email(message = "Invalid Email")
    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "tendangnhap")
    private String tendangnhap;

    @Column(name = "matkhau")
    @Size(min = 8, max = 20, message = "MK từ 8 -20 kí tự")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password không đúng định dạng")
    private String matkhau;

}
