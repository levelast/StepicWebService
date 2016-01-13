package ru.broject.stepic.web.auth.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.broject.stepic.web.auth.account.AccountService;
import ru.broject.stepic.web.auth.account.UserProfile;
import ru.broject.stepic.web.auth.servlet.SessionsServlet;
import ru.broject.stepic.web.auth.servlet.SignInServlet;
import ru.broject.stepic.web.auth.servlet.SignUpServlet;
import ru.broject.stepic.web.auth.servlet.UserServlet;

/**
 * Created by vyacheslav.svininyh on 12.01.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        accountService.addNewUser(new UserProfile("admin", "admin", "admin@mail"));
        accountService.addNewUser(new UserProfile("user", "user", "user@mail"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UserServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
