package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.awt.print.Book;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Table(name = "BookComment")
public class BookComment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column (name = "ngaylap")
    private LocalDateTime ngaylap;

    @Column (name = "title")
    private String title;

    @Column (name = "cmtcontent")
    private String comment;

    @Column (name = "star")
    private int star;

    @Column (name = "picture")
    private String pic;

    @JoinColumn(name="masach", referencedColumnName = "masach")
    @ManyToOne(fetch = FetchType.EAGER)
    private SACH masach;
}
