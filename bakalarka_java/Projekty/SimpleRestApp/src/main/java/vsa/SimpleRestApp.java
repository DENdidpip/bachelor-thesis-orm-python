/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package vsa;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author edu
 */
public class SimpleRestApp {

    public static final String BASE_URI = "http://localhost:8080/SimpleRestApp";

    static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources in rest package
        final ResourceConfig rc = new ResourceConfig().packages("rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Jersey app started with endpoints at " + BASE_URI);
        System.out.println("Hit Ctrl-C to stop it...");

        System.in.read();
        server.stop();
    }
}
