package ru.broject.stepic.web.orm.servlet;

import ru.broject.stepic.web.orm.model.UserProfile;
import ru.broject.stepic.web.orm.service.UserService;
import ru.broject.stepic.web.orm.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vyacheslav.svininyh on 18.01.2016.
 */
public class SignUpServlet extends HttpServlet {

    private final UserService userService;

    public SignUpServlet() {
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = userService.getUser(login);
        if (userProfile != null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("This login already exists");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        userService.addUser(new UserProfile(login, password));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Success registered");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
