package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.Blog;
import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Repository.BlogRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public ResponseEntity<String> sendmail(String to, String subject, String body) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("thovuanit2003@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            return ResponseEntity.ok("Send mail successful");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }



    public void sendWriteBlogConfirmMail(String to, String subject, Blog blog, KHACH guest, String view) throws MessagingException {
        //tao mimimessage va mimemessagehelper de soan va gui email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        //nhap email nguoi nhan va cc
        helper.setTo(to);
        helper.setSubject(subject);

        //goi context de chua du lieu
        Context context = new Context();
        context.setVariable("blog", blog);
        context.setVariable("guest", guest);

        //thêm danh sach vao
        String html = templateEngine.process(view, context);
        helper.setText(html, true);

        mailSender.send(message);
    }

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public Blog getBlog(String id) {
        return blogRepository.getReferenceById(id);
    }

    public Blog SaveBlog(Blog blog) {
        blogRepository.save(blog);
        return blog;
    }

    public void DeleteBlog(Blog blog) {
        blogRepository.delete(blog);
    }


}
