/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa.svetknihv4klient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class NewMain {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        Response resp = client
                .target("http://localhost:8080/SvetKnihV4/resources").path("SvetKnih")
                .request(MediaType.TEXT_PLAIN)
                .get(Response.class);

        for (Link l : resp.getLinks()) {
//            System.out.println("Link relation:" + l.getRel() + " URI:" + l.getUri());
            Response resp2 = client
                    .target(l)
                    .request(MediaType.APPLICATION_XML)
                    .get(Response.class);
            String obchod = resp2.readEntity(String.class);
            System.out.println(obchod);

            for (Link l2 : resp2.getLinks()) {
//                System.out.println("Link relation:" + l2.getRel() + " URI:" + l2.getUri());
                String kniha = client
                        .target(l2)
                        .request(MediaType.APPLICATION_XML)
                        .get(String.class);
                System.out.println(kniha);
            }
        }
    }
}
