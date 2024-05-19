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
@IdClass(CHITIETCHUCVU.class)
@Table(name = "CHITIETCHUCVU")
public class CHITIETCHUCVU {
    @Id
    @JoinColumn(name="manhanvien", referencedColumnName = "manhanvien")
    @ManyToOne(fetch = FetchType.EAGER)
    private NHANVIEN manhanvien;

    @Id
    @JoinColumn(name="machucvu", referencedColumnName = "machucvu")
    @ManyToOne(fetch = FetchType.EAGER)
    private CHUCVU machucvu;

    @Column(name = "thoihanhopdong")
    private LocalDateTime thoihanhopdong;
}
