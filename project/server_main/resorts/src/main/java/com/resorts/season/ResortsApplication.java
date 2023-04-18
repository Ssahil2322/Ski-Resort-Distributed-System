package com.resorts.season;

import com.resorts.season.servlet.ResortServlet;
import com.resorts.season.servlet.SkierServlet;
import com.resorts.season.servlet.StatisticsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class ResortsApplication {

    public static void main(String[] args) {
        try
        {
            Server server = new Server(7070);
            ServletContextHandler handler = new ServletContextHandler(server, "");

            handler.addServlet(SkierServlet.class, "/skiers/*");


            server.start();
            server.join();
        } catch (Exception ex)
        {

        }

    }

}
