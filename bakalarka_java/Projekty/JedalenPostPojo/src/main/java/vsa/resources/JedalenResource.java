/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa.resources;

import entities.Jedlo;
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

    //private List<String> jedla;                              
    private List<Jedlo> jedla;                              // POJO mod

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public JedalenResource() {
        jedla = new ArrayList<>();
        jedla.add(new Jedlo("Vyprazany syr",2.4));          // POJO mod
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPocet() {
        //TODO return proper representation object
        return "" + jedla.size();
    }


    @POST
    @Consumes(MediaType.APPLICATION_XML)                // POJO mod
    @Produces(MediaType.TEXT_PLAIN)
    public String pridajJedlo(Jedlo content) {          // POJO mod
        jedla.add(content);
        return "" + (jedla.size()-1);
    }

    @GET
    @Path("{cislo}")
    @Produces(MediaType.APPLICATION_XML)                // POJO mod
    public Jedlo getJedlo(@PathParam("cislo") int n) {  // POJO mod
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
