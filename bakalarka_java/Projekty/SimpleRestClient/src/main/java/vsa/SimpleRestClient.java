/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package vsa;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author edu
 */
public class SimpleRestClient {

    public static void main(String[] args) {
        String BASE_URI = "http://localhost:8080/SimpleRestApp";

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BASE_URI).path("message");

        // putting message to rest service
        String message = "Hello";
        webTarget.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(message, MediaType.TEXT_PLAIN));

        // getting message from rest service
        String result = webTarget.request(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println("GET returned: " + result);
    }
}
