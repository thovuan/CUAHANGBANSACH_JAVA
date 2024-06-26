package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table (name="NHANVIEN")
public class NHANVIEN {

    @Id
    @Column (name = "manhanvien")
    private String manhanvien;

    @Column (name="tennhanvien")
    private String tennhanvien;

    @Column(name = "tendangnhap")
    private String tendangnhap;

    @Size(min = 8, max = 20, message = "MK từ 8 -20 kí tự")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password không đúng định dạng")
    @Column(name = "matkhau")
    private String matkhau;

    @Column
    @Email(message = "Email invalid")
    private String email;

    @Column
    private String avatar;

    @Transient
    private String retypedpass;

    @OneToMany(mappedBy = "manhanvien", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CHITIETCHUCVU> chucVuDetails;
}
