package vsa.resources;

import java.util.List;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import java.util.ArrayList;

@Stateless                                                          
@Path("SvetKnih")
public class SvetKnihResource {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getZoznamObchodov() {
        TypedQuery tq = em.createQuery("SELECT k.nazov FROM Knihkupectvo k", String.class);
        List<String> names = tq.getResultList();
        return names;
    }

    @GET
    @Path("{obchod}")
    @Produces(MediaType.APPLICATION_XML)
    public Knihkupectvo getObchod(@PathParam("obchod") String obchod) {
        return em.find(Knihkupectvo.class, obchod);
    }

    @GET
    @Path("{obchod}/{isbn}")
    @Produces(MediaType.APPLICATION_XML)
    public Kniha getObchodKniha(@PathParam("obchod") String obchod, @PathParam("isbn") String isbn) {
        Knihkupectvo kn = em.find(Knihkupectvo.class, obchod);
        if (kn != null && kn.getPonuka() != null) {
            for (Kniha k : kn.getPonuka()) {
                if (isbn.equals(k.getIsbn())) {
                    return k;
                }
            }
        }
        return null;
    }

    @PUT
    @Path("{obchod}/{isbn}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putObchodKniha(@PathParam("obchod") String obchod, @PathParam("isbn") String isbn, Kniha content) {
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
