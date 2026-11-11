/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa.resources;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Path("menu")
public class JedalenResource {

    private Map<String, String> jedla;

    @Context
    private UriInfo context;

    public JedalenResource() {
        jedla = new HashMap<>();
        jedla.put("Syr", "Vyprazany syr, 3.50");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMenu() {
        String r = "";
        for (String s : jedla.keySet()) {
           r = r + s + " "; 
        }
        return r;
    }

    @PUT
    @Path("{nazov}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void pridajJedlo(@PathParam("nazov") String n, String content) {
        jedla.put(n, content);
    }

    @GET
    @Path("{nazov}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getJedlo(@PathParam("nazov") String n) {
        return jedla.get(n);
    }

    @DELETE
    @Path("{nazov}")
    public void odstranJedlo(@PathParam("nazov") String n) {
        jedla.remove(n);
    }

    
    
}
