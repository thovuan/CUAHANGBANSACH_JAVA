package com.cuahangbansach.cuahangbansach_java.Service;

import com.cuahangbansach.cuahangbansach_java.Model.KHACH;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class GuestIdentitySendMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void Register_ChangePassComplete(String to, String subject, KHACH guest, String view) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        //nhap email nguoi nhan va cc
        helper.setTo(to);
        helper.setSubject(subject);

        //goi context de chua du lieu
        Context context = new Context();
        context.setVariable("guest", guest);


        //thêm danh sach vao
        if (view != null) {
            String html = templateEngine.process(view, context);
            helper.setText(html, true);
        }


        mailSender.send(message);
    }
}
