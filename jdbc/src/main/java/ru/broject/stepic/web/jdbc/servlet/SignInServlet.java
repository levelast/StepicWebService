package ru.broject.stepic.web.jdbc.servlet;

import ru.broject.stepic.web.jdbc.model.UserProfile;
import ru.broject.stepic.web.jdbc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
public class SignInServlet extends HttpServlet {

    private final UserService userService;

    public SignInServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = userService.getUser(login);
        if (userProfile == null || !userProfile.getPass().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

//        String sessionId = request.getSession().getId();
//        userService.addSession(sessionId, userProfile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Authorized");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
