package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name ="THELOAISACH")
public class THELOAISACH {

    @Id
    @Column(name = "matheloai")
    private String matheloai;

    @Column(name = "tentheloai")
    private String tentheloai;

    @Column(name = "avatar")
    private String avatar;

    @Transient
    private int Count;
}
