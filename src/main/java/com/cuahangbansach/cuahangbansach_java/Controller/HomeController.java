package com.cuahangbansach.cuahangbansach_java.Controller;
import com.cuahangbansach.cuahangbansach_java.Model.*;
import com.cuahangbansach.cuahangbansach_java.Service.QLNXBService;
import com.cuahangbansach.cuahangbansach_java.Service.QLSACHService;
import com.cuahangbansach.cuahangbansach_java.Service.QLTHELOAIService;
import com.cuahangbansach.cuahangbansach_java.Service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger.*;


@Controller
public class HomeController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private QLNXBService qlnxbService;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private HttpSession httpSession;

    private static final Logger logger = LoggerFactory.getLogger(SACH.class);


    @GetMapping("/Home/index")
    public String homepage(Model model) {
        KHACH kyaku = (KHACH) httpSession.getAttribute("guest");
        if (kyaku == null) {

            httpSession.setAttribute("donhang", null);
        } else {
            PHIEUMUAHANG dh = shoppingCartService.GetDH(kyaku.getMakhachhang());
            httpSession.setAttribute("donhang", dh);
            //model.addAttribute("guest", kyaku);
        }

        List<THELOAISACH> tl = qltheloaiService.GetAll();
        model.addAttribute("tl", tl);

        List<NXB> nxb = qlnxbService.GetList();
        model.addAttribute("nxb", nxb);

        List<SACH> topsell = qlsachService.Top8BookSeller();
        logger.info("Top products to view: {}", topsell);
        model.addAttribute("topsell", topsell);

        return "/Home/index";  // Trả về trang index.html
    }


}
