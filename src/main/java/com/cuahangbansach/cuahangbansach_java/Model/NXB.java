package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "NXB")
public class NXB {

    @Id
    @Column(name = "manxb")
    private String manxb;

    @Column(name = "tennxb")
    private String tennxb;

    @Transient
    private int Count;
}
