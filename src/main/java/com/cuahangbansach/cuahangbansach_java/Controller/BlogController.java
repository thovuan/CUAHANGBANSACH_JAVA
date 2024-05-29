package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Service.BlogService;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentityService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private GuestIdentityService guestIdentityService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/About/Blog/Blog")
    public String Blog(Model model) {
        KHACH guest = (KHACH) httpSession.getAttribute("guest");
        model.addAttribute("DSBlog", guest.getBlogs());
        return "About/Blog/Blog";
    }

    @GetMapping("/About/Blog/Index")
    public String Index(Model model) {
        model.addAttribute("DSBlog", blogService.getAll());
        return "About/Blog/Index";
    }

    @GetMapping("/About/Blog/WriteBlog")
    public String WriteBlog(Model model) {
        return "/About/Blog/WriteBlog";
    }

    @PostMapping("/saveWord")
    public String saveWord(@RequestParam("text") String text, HttpServletResponse response) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);

        String filename = "document.docx";
        FileOutputStream out = new FileOutputStream(filename);
        document.write(out);
        out.close();

        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        return "/About/Blog/Complete";
    }
}
