package no.geekworld;

import org.apache.wicket.protocol.http.WicketServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import no.geekworld.pages.HelloWorldApplication;


/**
 * Class used to start the server
 */
public class ServerStarter {


    public static void main(String[] args) throws Exception {

        Server server = new Server(8888);

        ServletHolder servletHolder = new ServletHolder(new WicketServlet());
        servletHolder.setInitParameter("applicationClassName", HelloWorldApplication.class.getName());

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(servletHolder, "/*");

        server.setHandler(servletContextHandler);

        server.start();
        server.join();
    }

}


