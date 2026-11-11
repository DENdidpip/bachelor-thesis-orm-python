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
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("menu")
public class JedalenResource {

    private List<String> jedla;

    @Context
    private UriInfo context;

    public JedalenResource() {
        jedla = new ArrayList<>();
        jedla.add("Vyprazany syr");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPocet() {
        return "" + jedla.size();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String pridajJedlo(String content) {
        jedla.add(content);
        return "" + (jedla.size()-1);
    }

    @GET
    @Path("{cislo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getJedlo(@PathParam("cislo") int n) {
        if (n < 0 || n >= jedla.size()) {
            return null;
        }
        return jedla.get(n);
    }

    @DELETE
    @Path("{cislo}")
    public void odstranJedlo(@PathParam("cislo") int n) {
        if (n < 0 || n >= jedla.size()) {
            return;
        }
        jedla.remove(n);
    }

}
