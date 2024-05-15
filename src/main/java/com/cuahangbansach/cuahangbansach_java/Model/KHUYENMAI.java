package com.cuahangbansach.cuahangbansach_java.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Table(name = "KHUYENMAI")
public class KHUYENMAI {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    @Min(value = 0, message = "Discount error")
    private int discount;

    @Column(name = "quantity")
    @Min(value = 0, message = "Quantity error")
    private int quantity;


    @Column(name = "conditioncost")
    @Min(value = 0, message = "ConditionCost error")
    private long conditioncost;

}
