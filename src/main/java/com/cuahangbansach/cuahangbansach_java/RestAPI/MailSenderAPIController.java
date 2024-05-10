package com.cuahangbansach.cuahangbansach_java.RestAPI;

import com.cuahangbansach.cuahangbansach_java.Model.SACH;
import com.cuahangbansach.cuahangbansach_java.Service.EmailSenderService;
import com.cuahangbansach.cuahangbansach_java.Service.QLSACHService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import java.util.List;

@RestController
@RequestMapping("/api/MailSender")
public class MailSenderAPIController {

    @Autowired
    private EmailSenderService emailSender;

    @Autowired
    private QLSACHService qlsachService;

    @PostMapping
    public ResponseEntity<String> sendMail(String to, String subject, String body) throws MessagingException {
            emailSender.sendmail("vquang111972@gmail.com", "Test Mail Send", "おはよう世界");
            return ResponseEntity.ok("Send mail successful");
    }

    @PostMapping("/SendList")
    public ResponseEntity<String> sendMailList(String to, String subject, String body) throws MessagingException {
        List<SACH> hon = qlsachService.getList();
        emailSender.sendMail("haitengamer1234@gmail.com", "Book's List [Test]", hon);
        return ResponseEntity.ok("Send mail list successful");
    }
}
