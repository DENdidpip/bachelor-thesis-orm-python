/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa.resources;

import entities.Jedlo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

@Stateless
//@Singleton
@Path("menu")
public class JedalenResource {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

//    private List<Jedlo> jedla;

    @Context
    private UriInfo context;

    public JedalenResource() {
//        jedla = new ArrayList<>();
//        jedla.add(new Jedlo("Vyprazany syr",2.4));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public long getPocet() {
        TypedQuery<Jedlo> tq = em.createQuery("select j from Jedlo j", Jedlo.class);
        return tq.getResultList().size();
    }


    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public long pridajJedlo(Jedlo content) {
//   JTA otvara a ukoncuje transakciu automaticky pre kazdy request
//        System.out.println("pred persist: " +content);
        em.persist(content);
//        System.out.println("po persist: " +content);
        return content.getId(); // id je relativna URL
    }

    @GET
    @Path("{cislo}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedlo(@PathParam("cislo") long n) {
        return em.find(Jedlo.class, n);
    }

    @DELETE
    @Path("{cislo}")
    public void odstranJedlo(@PathParam("cislo") long n) {
        Object r =  em.getReference(Object.class, n);
        em.remove(r);
    }
     
}
