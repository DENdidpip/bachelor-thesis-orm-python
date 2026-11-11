/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("message")
@Singleton
public class SimpleResource {
    private String oznam = "ziadny oznam";
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return oznam;
    }


    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putMessage(String content ) {
       oznam = content;
    }
}
