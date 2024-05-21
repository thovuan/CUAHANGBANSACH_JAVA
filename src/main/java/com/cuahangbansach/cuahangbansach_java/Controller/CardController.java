package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Model.THE;
import com.cuahangbansach.cuahangbansach_java.Service.CardService;
import com.cuahangbansach.cuahangbansach_java.Service.EmailSenderService;
import com.cuahangbansach.cuahangbansach_java.Service.QLKHACHService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private QLKHACHService qlkhachService;

    @Autowired
    private EmailSenderService mailSenderService;

    @Autowired
    private HttpSession httpSession;


    @GetMapping("/Identity/Guest/CardDetail/{id}")
    public String GuestCardDetail(Model model, @PathVariable String id) {
        THE card = cardService.FindThe(id);
        model.addAttribute("card", card);

        return "/Identity/Guest/CardDetail";
    }

    @PostMapping("/Identity/Guest/AddCard")
    public String AddCard(Model model) {
        KHACH khach = (KHACH) httpSession.getAttribute("guest");
        THE card = cardService.FindThe(khach.getMakhachhang());
        if (card != null) {
            model.addAttribute("errorMessage", "Không thể tạo thẻ");
            return "/Identity/Guest/CardDetail";
        }

        LocalDateTime dt = LocalDateTime.now();
        THE the = new THE();
        the.setMathe("THE" + dt.getYear() + dt.getMonthValue() + dt.getDayOfMonth() + dt.getHour() + dt.getMinute() + dt.getSecond());
        the.setDiemthe(1000);
        the.setMakhachhang(khach);

        try{
            cardService.Add(the);
            mailSenderService.addCardConfirmMail(khach.getEmail(), "Xác nhận tạo thẻ #"+the.getMathe(), the, khach, "/Identity/Guest/AddCardComplete" );
            return "redirect:/Identity/Guest/CardDetail";

        }catch(Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/Identity/Guest/CardDetail";
        }
    }

    @GetMapping("/Identity/Guest/DeleteCard")
    public String DeleteCard(Model model) {
        KHACH khach = (KHACH) httpSession.getAttribute("guest");
        THE card = cardService.FindThe(khach.getMakhachhang());
        if (card != null) {
            try {
                cardService.Delete(card);
                return "redirect:/Identity/Guest/CardDetail";

            } catch (Exception e) {model.addAttribute("errorMessage", e.getMessage());
                return "/Identity/Guest/CardDetail";

            }
        }
        model.addAttribute("errorMessage", "Card not Found");
        return "/Identity/Guest/CardDetail";

    }
}
