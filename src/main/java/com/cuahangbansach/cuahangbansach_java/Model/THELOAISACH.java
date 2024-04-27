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
    public String matheloai;

    @Column(name = "tentheloai")
    public String tentheloai;

    @Transient
    public int Count;
}
