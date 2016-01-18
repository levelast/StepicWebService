package ru.broject.stepic.web.jdbc.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.broject.stepic.web.jdbc.service.UserService;
import ru.broject.stepic.web.jdbc.service.UserServiceImpl;
import ru.broject.stepic.web.jdbc.servlet.SignInServlet;
import ru.broject.stepic.web.jdbc.servlet.SignUpServlet;

/**
 * Created by vyacheslav.svininyh on 15.01.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception {

//        DBHelper.printConnectInfo();
//        UserService userService = new UserServiceImpl();
//        userService.createTable();
//        userService.addUser(new UserProfile("admin", "pass"));
//        UserProfile userProfile = userService.getUser("admin");
//        System.out.println(userProfile);
//        userService.dropTable();

        UserService userService = UserServiceImpl.getInstance();
        userService.createTable();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(userService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(userService)), "/signup");

//        ResourceHandler resourceHandler = new ResourceHandler();
//        resourceHandler.setResourceBase("html");
//
//        HandlerList handlers = new HandlerList();
//        handlers.setHandlers(new Handler[] {resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();

//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//
//        ResourceHandler resourceHandler = new ResourceHandler();
//        resourceHandler.setResourceBase("html");
//
//        HandlerList handler = new HandlerList();
//        handler.setHandlers(new Handler[] {resourceHandler, context});
//
//        Server server = new Server(8080);
//        server.setHandler(handler);
//
//        server.start();
//        server.join();
    }
}
