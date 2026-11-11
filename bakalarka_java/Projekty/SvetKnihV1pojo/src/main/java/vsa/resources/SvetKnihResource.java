/**
 * NEDAVAT STUDENTOM na stiahnutie !!!
 * Implementecia len pre pouzitie na prednaskach
 */
package vsa.resources;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import pojo.Kniha;
import pojo.Knihkupectvo;
import java.util.ArrayList;

@Singleton
@Path("SvetKnih")
public class SvetKnihResource {

    List<Knihkupectvo> obchody;

    public SvetKnihResource() {
        init();
    }

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getZoznamObchodov() {

        List<String> nazvy = new ArrayList<>();
        for (Knihkupectvo k : obchody) {
            nazvy.add(k.getNazov());
        }
        return nazvy;
    }

    @GET
    @Path("{obchod}")
    @Produces(MediaType.APPLICATION_XML)
    public Knihkupectvo getObchod(@PathParam("obchod") String obchod) {
        for (Knihkupectvo kn : obchody) {
            if (obchod.equals(kn.getNazov())) {
                return kn;
            }
        }
        return null;
    }

    @GET
    @Path("{obchod}/{isbn}")
    @Produces(MediaType.APPLICATION_XML)
    public Kniha getObchodKniha(@PathParam("obchod") String obchod, @PathParam("isbn") String isbn) {
        for (Knihkupectvo kn : obchody) {
            if (obchod.equals(kn.getNazov())) {
                for (Kniha k : kn.getPonuka()) {
                    if (isbn.equals(k.getIsbn())) {
                        return k;
                    }
                }
                // nenasiel knihu
                return null;
            }
        }
        // nenasiel obchod
        return null;
    }

    @PUT
    @Path("{obchod}/{isbn}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putObchodKniha(@PathParam("obchod") String obchod, @PathParam("isbn") String isbn, Kniha content) {
        // pre istotu
        content.setIsbn(isbn);
        for (Knihkupectvo kn : obchody) {
            if (obchod.equals(kn.getNazov())) {
                // ak kniha je v ponuke odstranim ju
                for (Kniha k : kn.getPonuka()) {
                    if (isbn.equals(k.getIsbn())) {
                        kn.getPonuka().remove(k);
                        break;
                    }
                }
                kn.getPonuka().add(content);
            }
        }
    }

    public void init() {
        obchody = new ArrayList<>();

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
    }
}
