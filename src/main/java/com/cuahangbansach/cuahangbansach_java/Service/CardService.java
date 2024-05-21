package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.THE;
import com.cuahangbansach.cuahangbansach_java.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public THE FindThe(String makhach) {

        return cardRepository.findByKHACH(makhach);
    }

    public void UpdateCardPoint(THE theCard) {
        cardRepository.save(theCard);
    }

    public THE Add(THE theCard) {
        return cardRepository.save(theCard);
    }

    public void Delete(THE theCard) {
        cardRepository.delete(theCard);
    }
}
