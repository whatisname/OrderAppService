package com.xxy.ordersystem.service.UpperService.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.manager
 * @date 9/6/2018 5:57 PM
 */
@Component
@Slf4j
@ServerEndpoint("/manage/websocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArrayList<WebSocket> webSockets = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("【websocket】 new connection, current connections: {}", webSockets.size());
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【websocket】 new connection, current connections: {}", webSockets.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【websocket】receive message from client: {}", message);
    }

    /**
     * Broadcast message to clients
     * @param message 要发送的消息
     */
    public void sendMessage(String message){
        for (WebSocket webSocket: webSockets){
            log.info("【websocket】send message to client, message: \"{}\"", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
