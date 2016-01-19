package ru.broject.stepic.web.orm.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.broject.stepic.web.orm.servlet.SignInServlet;
import ru.broject.stepic.web.orm.servlet.SignUpServlet;

import java.util.logging.Logger;

/**
 * Created by vyacheslav.svininyh on 15.01.2016.
 */
public class Main {

    private static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet()), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet()), "/signup");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        logger.info("Server started");
        server.join();
    }
}
