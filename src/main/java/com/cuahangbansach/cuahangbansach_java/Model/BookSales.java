package com.cuahangbansach.cuahangbansach_java.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
public class BookSales {

    private String masach;
    private long totalSolid;


}
