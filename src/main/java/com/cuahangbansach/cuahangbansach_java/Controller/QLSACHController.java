package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.DAO.QLSACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QLSACHController {
    @Autowired
    private QLSACHRepository qlsachRepository;
    @GetMapping("/QLSACH/Index")
    public String QLSACHIndex(Model model) {
        List<SACH> list= qlsachRepository.GetList();
        model.addAttribute("SACHList", list);
        return "QLSACH/Index";
    }
}
