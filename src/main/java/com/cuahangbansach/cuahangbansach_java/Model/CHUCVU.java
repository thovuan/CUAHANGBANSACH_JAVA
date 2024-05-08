package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "CHUCVU")
public class CHUCVU {

    @Id
    @Column(name = "machucvu")
    private String machucvu;

    @NotNull(message = "Ten chuc vu khong duoc trong")
    @Column (name = "tenchucvu")
    private String tenchucvu;
}
