package ru.broject.stepic.web.websockets.servlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ru.broject.stepic.web.websockets.service.ChatService;
import ru.broject.stepic.web.websockets.service.ChatServiceImpl;
import ru.broject.stepic.web.websockets.socket.ChatWebSocket;

import javax.servlet.annotation.WebServlet;

/**
 * Created by vyacheslav.svininyh on 19.01.2016.
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final ChatService chatService;

    public WebSocketChatServlet() {
        this.chatService = new ChatServiceImpl();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}