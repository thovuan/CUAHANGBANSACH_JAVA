package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Table(name = "Blog")
public class Blog {

    @Id
    @Column(name = "id_blog")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "ngayviet")
    private LocalDateTime ngayviet;

    @Column(name = "title")
    private String title;

    @Column(name = "Bcontent")
    private String content;

    @Column(name = "picture")
    private String pic;

    @JoinColumn(name="makhachhang", referencedColumnName = "makhachhang")
    @ManyToOne(fetch = FetchType.EAGER)
    private KHACH makhachhang;

}
