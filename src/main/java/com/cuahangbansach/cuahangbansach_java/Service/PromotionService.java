package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.KHUYENMAI;
import com.cuahangbansach.cuahangbansach_java.Repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<KHUYENMAI> getList() {
        return promotionRepository.findAll();
    }

    public List<KHUYENMAI> GetSuggetedPromotion(long DHCost) {
        return promotionRepository.GetSuggestPromotion(DHCost);
    }

    public KHUYENMAI getById(String id) {
        return promotionRepository.getReferenceById(id);
    }

    public KHUYENMAI Save(KHUYENMAI khuyenmai) {
        return promotionRepository.save(khuyenmai);
    }
}
