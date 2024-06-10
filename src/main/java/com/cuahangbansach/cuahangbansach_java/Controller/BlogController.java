package com.cuahangbansach.cuahangbansach_java.Controller;

import com.cuahangbansach.cuahangbansach_java.Model.Blog;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Service.BlogService;
import com.cuahangbansach.cuahangbansach_java.Service.GuestIdentityService;
import com.cuahangbansach.cuahangbansach_java.Service.QLKHACHService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private GuestIdentityService guestIdentityService;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private QLKHACHService qlkhachService;

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
        model.addAttribute("blog", new Blog());
        return "/About/Blog/WriteBlog";
    }

    @PostMapping("/saveBlog")
    public String saveWord(Model model,@RequestBody @Valid @ModelAttribute("blog")Blog blog, @RequestParam("image") MultipartFile multipartFile, HttpServletResponse response, BindingResult bindingResult) throws IOException {
//        XWPFDocument document = new XWPFDocument();
//        XWPFParagraph paragraph = document.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        run.setText(text);
//
//        String filename = "document.docx";
//        FileOutputStream out = new FileOutputStream(filename);
//        document.write(out);
//        out.close();
//
//        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi xảy ra trong quá trình xác thực, quay lại view tạo mới sách và hiển thị thông báo lỗi
            return "/About/Blog/WriteBlog";
        }
        KHACH kyaku = (KHACH) httpSession.getAttribute("guest");

        LocalDateTime dt = LocalDateTime.now();
        Blog bl = new Blog();
        bl.setNgayviet(dt);
        bl.setTitle(blog.getTitle());
        bl.setMakhachhang(qlkhachService.getKhachById(kyaku.getMakhachhang()));

        //xu ly text
        String sanitizedText = Jsoup.clean(blog.getContent(), Safelist.none());
        bl.setContent(sanitizedText);

        //them anh
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        System.out.println(fileName);
        bl.setPic(fileName);

        try {

            Blog blogg = blogService.SaveBlog(bl);

            String uploadDir = "./src/main/resources/static/Blog_Picture/" + blogg.getId();
            Path uploadPath =  Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            try (InputStream inputStream = multipartFile.getInputStream()){
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);


            } catch (IOException _) {
                model.addAttribute("errorMessage", "Loi cập nhật ảnh");
                return "/About/Blog/WriteBlog";
            }

            blogService.sendWriteBlogConfirmMail(kyaku.getEmail(), "Thông báo đã thêm Blog thành công", bl, kyaku, "/About/Blog/WriteBlogComplete");
            return "/About/Blog/Complete";

        } catch (Exception ex){
            model.addAttribute("errorMessage", "Loi them Blog");
            return "/About/Blog/WriteBlog";
        }


    }

    @GetMapping("/Revenue/Blog/Index")
    public String BlogRevenue(Model model) {
        model.addAttribute("chartData", blogService.getBlogRevenue(2024));
        return "/Revenue/Blog/Index";
    }
}
