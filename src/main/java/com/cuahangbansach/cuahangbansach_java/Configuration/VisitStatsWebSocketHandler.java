//package com.cuahangbansach.cuahangbansach_java.Configuration;
//
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class VisitStatsWebSocketHandler extends TextWebSocketHandler {
//
//    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.add(session);
//    }
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // Handle incoming message if needed
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
//        sessions.remove(session);
//    }
//
//    public static void broadcast(String message) throws Exception {
//        for (WebSocketSession session : sessions) {
//            session.sendMessage(new TextMessage(message));
//        }
//    }
//}
