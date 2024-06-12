package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.CHITIETDATHANG;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private QLKHACHRepository qlkhachRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    public QLSACHRepository ql;

    @Autowired
    public DetailSCRepository detailSCRepository;

    public long TotalRevenue() {
        List<PHIEUMUAHANG> pmh = shoppingCartRepository.findAll();
        long TR = 0;
        for(PHIEUMUAHANG ph : pmh) {
            TR += ph.getDHTotal();
        }
        return TR;
    }

    public int UserNumber() {
        return qlkhachRepository.findAll().size();
    }

    public long CartSize() {
        return shoppingCartRepository.findAll().size();
    }

    public long BlogActivity() {
        return blogRepository.findAll().size();
    }

    public int BookSellerRe() {
        List<CHITIETDATHANG> pmh = detailSCRepository.findAll();
        int s = 0;
        for (CHITIETDATHANG ctdh : pmh) {
            s+= ctdh.getSoluongmua();
        }
        return s;
    }
}
