package ru.broject.stepic.web.auth.servlet;

import ru.broject.stepic.web.auth.account.AccountService;
import ru.broject.stepic.web.auth.account.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vyacheslav.svininyh on 13.01.2016.
 */
public class SignInServlet extends HttpServlet {

    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
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

        UserProfile userProfile = accountService.getUserByLogin(login);
        if (userProfile == null || !userProfile.getPass().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String sessionId = request.getSession().getId();
        accountService.addSession(sessionId, userProfile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Authorized");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
