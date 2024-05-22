//package com.cuahangbansach.cuahangbansach_java.Service;
//
//import com.cuahangbansach.cuahangbansach_java.Configuration.VisitStatsWebSocketHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//
//@Service
//public class RedisService {
//
//    private static final String ACTIVE_USERS_KEY = "activeUsers";
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    public void addActiveUser(String sessionId, String userIp) {
//        String key = sessionId + ":" + userIp;
//        redisTemplate.opsForValue().set(key, "active", Duration.ofMinutes(30));
//        redisTemplate.opsForSet().add(ACTIVE_USERS_KEY, key);
//    }
//
//    public void removeActiveUser(String sessionId, String userIp) {
//        String key = sessionId + ":" + userIp;
//        redisTemplate.delete(key);
//        redisTemplate.opsForSet().remove(ACTIVE_USERS_KEY, key);
//    }
//
//    public Long countActiveUsers() {
//        return redisTemplate.opsForSet().size(ACTIVE_USERS_KEY);
//    }
//
//    private void broadcastActiveUsers() {
//        try {
//            Long activeUsers = countActiveUsers();
//            VisitStatsWebSocketHandler.broadcast("Active Users: " + activeUsers);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
