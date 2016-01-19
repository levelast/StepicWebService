package ru.broject.stepic.web.websockets.service;

import ru.broject.stepic.web.websockets.socket.ChatWebSocket;

/**
 * Created by vyacheslav.svininyh on 19.01.2016.
 */
public interface ChatService {

    void sendMessage(String data);

    void add(ChatWebSocket webSocket);

    void remove(ChatWebSocket webSocket);
}
