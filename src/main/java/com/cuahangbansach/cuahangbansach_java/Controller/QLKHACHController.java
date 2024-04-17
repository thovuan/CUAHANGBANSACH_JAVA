package com.cuahangbansach.cuahangbansach_java.Controller;
import java.util.List;
import org.springframework.ui.Model;
import com.cuahangbansach.cuahangbansach_java.DAO.QLKHACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QLKHACHController {
    @Autowired private QLKHACHRepository qlkhachRepository;

    @GetMapping("/QLKHACH/Index")
    public String index(Model model) {
        List<KHACH> list = qlkhachRepository.GetList();
        model.addAttribute("KhachList", list);
        return "QLKHACH/Index";
    }
}
