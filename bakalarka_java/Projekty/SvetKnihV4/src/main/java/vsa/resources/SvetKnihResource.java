package vsa.resources;

import entities.Kniha;
import entities.Knihkupectvo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("SvetKnih")
public class SvetKnihResource {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getZoznamObchodov(@Context UriInfo uriInfo) {
        TypedQuery tq = em.createQuery("SELECT k.nazov FROM Knihkupectvo k", String.class);
        List<String> names = tq.getResultList();
        Response.ResponseBuilder rb = Response.ok("URI obchodov su v hlavicke");
        for (String nazov : names) {
            // link s URL pre subresource /SvetKnih/{obchod}
            rb = rb.link(uriInfo.getRequestUriBuilder().path(nazov).build(), "obchod");
        }
        return rb.build();
    }

    @GET
    @Path("{obchod}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getObchod(@Context UriInfo uriInfo, @PathParam("obchod") String obchod) {
        Knihkupectvo kk = em.find(Knihkupectvo.class, obchod);
        if (kk != null && kk.getPonuka() != null) {
            Response.ResponseBuilder rb = Response.ok(kk);
            for (Kniha k : kk.getPonuka()) {
                // link pre URL resources/SvetKnih/kniha/{isbn}
                rb = rb.link(uriInfo.getRequestUriBuilder()
                        .path("..")
                        .path("kniha")
                        .path(k.getIsbn())
                        .build(), "ponuka");
                // takisto mozno pouzit
//                rb = rb.link(uriInfo.getAbsolutePathBuilder().path("..").path("kniha").path(k.getIsbn()).build(), "kniha");
//                rb = rb.link(uriInfo.getBaseUriBuilder().path("SvetKnih").path("kniha").path(k.getIsbn()).build(), "kniha");
            }
            return rb.build();
        }
        return null;
    }

    @GET
    @Path("kniha")
    @Produces(MediaType.TEXT_PLAIN)
    public Response findKnihy(@QueryParam("pattern") String pattern, @Context UriInfo uriInfo) {
        TypedQuery tq = em.createQuery("SELECT k.isbn FROM Kniha k WHERE k.titul LIKE '%" + pattern + "%'", String.class);
        List<String> lst = tq.getResultList();
        Response.ResponseBuilder rb = Response.ok("URI knih su v hlavicke");
        for (String isbn : lst) {
            // link s URL pre subresource /SvetKnih/kniha/{isbn}
            rb = rb.link(uriInfo.getRequestUriBuilder().path("kniha").path(isbn).build(), "isbn");
        }
        return rb.build();
    }

    @GET
    @Path("kniha/{isbn}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getKniha(@PathParam("isbn") String isbn, @Context UriInfo uriInfo) {
        Kniha k = em.find(Kniha.class, isbn);
//        System.out.println(""+ k.getPredajne().size());
        if (k != null && k.getPredajne() != null) {
            Response.ResponseBuilder rb = Response.ok(k);
            for (Knihkupectvo kk : k.getPredajne()) {
                // link pre URL resources/SvetKnih/{obchod}
                rb = rb.link(uriInfo.getBaseUriBuilder().path("SvetKnih").path(kk.getNazov())
                        .build(), "predajna");
//                System.out.println(""+ rb);
            }
//                System.out.println(""+ rb);
            return rb.build();
        }
        return null;
    }

    //   Modifikuje knihu globalne - pre vsetky knihkupectva
    //   Pozn. umozuje aj vytvarat nove knihy
    @PUT
    @Path("kniha/{isbn}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putKniha(@PathParam("isbn") String isbn, Kniha content) {
        content.setIsbn(isbn);
        em.merge(content);
    }

    // len pre inicializaciu DB
    @GET
    @Path("init")
    public void init() {
        List<Knihkupectvo> obchody = new ArrayList<>();
        Kniha k1 = new Kniha("111", "Hobit", 6.5);
        Kniha k2 = new Kniha("222", "Pipi dlha pancucha", 7.5);
        Kniha k3 = new Kniha("333", "Mach a Sebestova", 7.5);
        Kniha k4 = new Kniha("444", "Farma zvierat", 9.5);
        Kniha k5 = new Kniha("555", "Putnikov sprievodca galaxiou", 9.5);

        Knihkupectvo m = new Knihkupectvo("Martinus", null);
        Knihkupectvo p = new Knihkupectvo("PantaRhei", null);

        m.getPonuka().add(k1);
        m.getPonuka().add(k2);
        m.getPonuka().add(k3);

        p.getPonuka().add(k1);
        p.getPonuka().add(k4);
        p.getPonuka().add(k5);

        obchody.add(p);
        obchody.add(m);
        em.persist(p);
        em.persist(m);
        em.persist(k1);
        em.persist(k2);
        em.persist(k3);
        em.persist(k4);
        em.persist(k5);
    }
}
