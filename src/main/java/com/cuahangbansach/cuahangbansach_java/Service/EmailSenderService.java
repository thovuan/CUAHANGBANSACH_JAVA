package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import com.cuahangbansach.cuahangbansach_java.Model.PHIEUMUAHANG;
import com.cuahangbansach.cuahangbansach_java.Model.SACH;
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
public class EmailSenderService {
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

       public void sendMail(String to, String subject, List<SACH>hon) throws MessagingException {
           //tao mimimessage va mimemessagehelper de soan va gui email
           MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

           //nhap email nguoi nhan va cc
           helper.setTo(to);
           helper.setSubject(subject);

            //goi context de chua du lieu
           Context context = new Context();
            context.setVariable("SACHList", hon);

            //thêm danh sach vao
            String html = templateEngine.process("QLSACH/TakeList", context);
            helper.setText(html, true);

           mailSender.send(message);
       }

    public void sendCartConfirmMail(String to, String subject, PHIEUMUAHANG pmh, KHACH guest, String view) throws MessagingException {
        //tao mimimessage va mimemessagehelper de soan va gui email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        //nhap email nguoi nhan va cc
        helper.setTo(to);
        helper.setSubject(subject);

        //goi context de chua du lieu
        Context context = new Context();
        context.setVariable("pmh", pmh);
        context.setVariable("user", guest);

        //thêm danh sach vao
        String html = templateEngine.process(view, context);
        helper.setText(html, true);

        mailSender.send(message);
    }
}
