package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.NHANVIEN;
import com.cuahangbansach.cuahangbansach_java.Model.NXB;
import com.cuahangbansach.cuahangbansach_java.Model.THELOAISACH;
import com.cuahangbansach.cuahangbansach_java.Repository.QLSACHRepository;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Service.*;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller

public class QLSACHController {
    @Autowired
    private QLSACHRepository qlsachRepository;

    @Autowired
    private QLSACHService qlsachService;

    @Autowired
    private QLTHELOAIService qltheloaiService;

    @Autowired
    private QLNXBService qlnxbService;

    @Autowired
    private QLNVService qlnvService;
    @Autowired
    private HttpSession httpSession;


    @Autowired
    private StaffIdentityService staffIdentityService;

    private Boolean CheckPermit() {
        NHANVIEN nv = (NHANVIEN) httpSession.getAttribute("nv");
        if (nv != null) return staffIdentityService.CheckPermit(nv.getManhanvien(), "CV02");
        return false;
    }

    @GetMapping("/QLSACH/Index")
    public String QLSACHIndex(Model model, @RequestParam(name = "bookname", required = false) String bookname) {
//
            if (!CheckPermit())
                return "redirect:/Error/ErrorMe?mess=" + "Permission denied";
            //else return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        List<SACH> list;
        if (bookname != null) {
            list = qlsachService.getSACHByName(bookname);
        } else {
            list = qlsachService.getList();
        }
        model.addAttribute("SACHList", list);

        return "QLSACH/Index";
    }


    @GetMapping("/QLSACH/Create")
    public String AddSachGet(Model model) {
        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        List<THELOAISACH> list= qltheloaiService.GetAll();
        List<NXB> NXBlist = qlnxbService.GetList();

        model.addAttribute("sach", new SACH());
        model.addAttribute("TLList", list);
        model.addAttribute("NXBList", NXBlist);
        return "QLSACH/Create";
    }
    @PostMapping("/QLSACH/Add")
    public String AddSachPost(Model model, @RequestBody @Valid @ModelAttribute("sach") SACH sach, @RequestParam("image") MultipartFile multipartFile, HttpSession httpSession, BindingResult bindingResult) {
//        if (nv!=null) {
//            if (!staffIdentityService.CheckPermit(nv.getManhanvien(), "CV02"))
//                return "redirect:/Error/ErrorMe?mess=" + "Permission denied";
//        } else return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        if (bindingResult.hasErrors()) {
            // Nếu có lỗi xảy ra trong quá trình xác thực, quay lại view tạo mới sách và hiển thị thông báo lỗi
            return "/QLSACH/Create";
        }

        //them sach
        SACH hon = new SACH();
        hon.setMasach(sach.getMasach());
        hon.setTensach(sach.getTensach());
        hon.setSoluonghienco(sach.getSoluonghienco());
        hon.setDacdiem(sach.getDacdiem());
        hon.setDongia(sach.getDongia());
        hon.setDVT(sach.getDVT());
        hon.setTheloaisach(qltheloaiService.GetCategoryById(sach.getTheloaisach().getMatheloai()));
        hon.setNxb(qlnxbService.GetNXBById(sach.getNxb().getManxb()));
        hon.setNhanvien(qlnvService.GetById("NV01"));

        //them anh
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(fileName);
        hon.setAnhsanpham(fileName);


        try {
            //them sach
            SACH savesach = qlsachService.Create(hon);

            //cap nhat anh vao
            String uploadDir = "./src/main/resources/static/ANHSANPHAM/" + savesach.getMasach();
            Path uploadPath =  Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            try (InputStream inputStream = multipartFile.getInputStream()){
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);


            } catch (IOException _) {
                return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";
            }


            //return "redirect:/QLSACH/Index";
            return "redirect:/QLSACH/Index";

        } catch (Exception ex) {
            //return "Lỗi";
            //return "/Error/error";
            return "/QLSACH/Create";
        }
    }

    @PostMapping("/QLSACH/Save")
    private String SaveSACH(Model model, @ModelAttribute("sach")SACH sach, @RequestParam("image")MultipartFile multipartFile, HttpSession httpSession, BindingResult bindingResult) {
        SACH hon = qlsachService.GetSachById(sach.getMasach());
        //hon.setMasach(sach.getMasach());
        hon.setTensach(sach.getTensach());
        hon.setSoluonghienco(sach.getSoluonghienco());
        hon.setDacdiem(sach.getDacdiem());
        hon.setDongia(sach.getDongia());
        hon.setDVT(sach.getDVT());
        hon.setTheloaisach(qltheloaiService.GetCategoryById(sach.getTheloaisach().getMatheloai()));
        hon.setNxb(qlnxbService.GetNXBById(sach.getNxb().getManxb()));
        //hon.setNhanvien(qlnvService.GetById("NV01"));

        //them anh
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(fileName);
        hon.setAnhsanpham(fileName);

        try {
            //CAP NHAT SACH
            SACH savesach = qlsachService.Create(hon);

            //cap nhat anh vao
            String uploadDir = "./src/main/resources/static/ANHSANPHAM/" + savesach.getMasach();
            Path uploadPath =  Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            try (InputStream inputStream = multipartFile.getInputStream()){
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);


            } catch (IOException _) {
                return "redirect:/Error/ErrorMe?mess=" + "Lỗi không tìm thy";
            }
            //return "redirect:/QLSACH/Index";
            return "redirect:/QLSACH/Index";

        } catch (Exception ex) {
            //return "Lỗi";
            return "/Error/error";
        }
    }



    @GetMapping("/QLSACH/Edit/{id}")
    public String QLSACHEdit(Model model, @PathVariable("id") String id) {

        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        List<THELOAISACH> list= qltheloaiService.GetAll();
        List<NXB> NXBlist = qlnxbService.GetList();
        SACH sach = qlsachService.GetSachById(id);
        if (sach != null) {
            SACH hon = new SACH();
            //hon = sach;
            hon.setMasach(sach.getMasach());
            hon.setTensach(sach.getTensach());
            hon.setSoluonghienco(sach.getSoluonghienco());
            hon.setDacdiem(sach.getDacdiem());
            hon.setDongia(sach.getDongia());
            hon.setDVT(sach.getDVT());
            hon.setTheloaisach(qltheloaiService.GetCategoryById(sach.getTheloaisach().getMatheloai()));
            hon.setNxb(qlnxbService.GetNXBById(sach.getNxb().getManxb()));
//        hon.setNhanvien(sach.getNhanvien());
            model.addAttribute("sach", hon);
            model.addAttribute("TLList", list);
            model.addAttribute("NXBList", NXBlist);
            return "QLSACH/Edit";
        }
        return "Loi";
    }


    @GetMapping("/QLSACH/Details/{id}")
    public String Details(Model model, @PathVariable("id") String id) {

        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        SACH sach = qlsachService.GetSachById(id);
        if (sach == null) return "Can't Find Book: " + id;
        model.addAttribute("SACH", sach);
        return "QLSACH/Details";
    }

    @GetMapping("/QLSACH/Delete/{id}")
    public String Delete(SACH sach, @PathVariable("id")String id) {

        if (!CheckPermit())
            return "redirect:/Error/ErrorMe?mess=" + "Permission denied";

        SACH hon = qlsachService.GetSachById(id);
        if (hon == null) return "Can't Find Book: " + id;

        try {
            qlsachService.Delete(id);
            return "redirect:/QLSACH/Index";
        } catch(Exception ex) {
            return "Can't Delete Sach: " + id;
        }


    }


}
