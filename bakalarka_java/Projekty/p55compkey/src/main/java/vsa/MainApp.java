package vsa;

import entities.Zaznam;
import entities.ZaznamPK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class MainApp {

    public static void main(String[] args) {
        create();
    }
    
    public static void create() {
        ZaznamPK pk1 = new ZaznamPK();
        pk1.setPacient("JM");
        pk1.setDatum(LocalDate.now());           
        Zaznam z1 = new Zaznam();
        z1.setPk(pk1);
        z1.setTeplota(36.1);

        ZaznamPK pk2 = new ZaznamPK();
        pk2.setPacient("JM");
        pk2.setDatum(LocalDate.now().minusDays(2));           
        Zaznam z2 = new Zaznam();
        z2.setPk(pk2);
        z2.setTeplota(37.8);
        
        persist(z1);
        persist(z2);       
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
