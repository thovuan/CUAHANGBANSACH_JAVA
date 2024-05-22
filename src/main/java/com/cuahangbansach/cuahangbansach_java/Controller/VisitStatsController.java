//package com.cuahangbansach.cuahangbansach_java.Controller;
//
//import com.cuahangbansach.cuahangbansach_java.Service.RedisService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSessionEvent;
//import jakarta.servlet.http.HttpSessionListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.io.Serializable;
//
//@Controller
//public class VisitStatsController implements HttpSessionListener {
//
//    @Autowired
//    private RedisService redisService;
//
//    @Override
//    public void sessionCreated(HttpSessionEvent se) {
//        // Do nothing
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent se) {
//        String sessionId = se.getSession().getId();
//        String userIp = (String) se.getSession().getAttribute("userIp");
//        if (userIp != null) {
//            redisService.removeActiveUser(sessionId, userIp);
//        }
//    }
//
//    @GetMapping("/VisitStats")
//    public String Sess (HttpServletRequest request, Model model) {
//        // Lấy sessionId và userIp từ HttpServletRequest
//        String sessionId = request.getSession().getId();
//        String userIp = request.getRemoteAddr();
//
//        // Lưu thông tin truy cập vào Redis
//        redisService.addActiveUser(sessionId, userIp);
//        request.getSession().setAttribute("userIp", userIp);
//
//        // Lấy số liệu
//        Long activeVisitors = redisService.countActiveUsers();
//
//        // Thêm số liệu vào model
//        model.addAttribute("activeVisitors", activeVisitors);
//
//        return "/Shared/VisitStats";
//    }
//}
