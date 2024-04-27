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
    public String manxb;

    @Column(name = "tennxb")
    public String tennxb;

    @Transient
    public int Count;
}
