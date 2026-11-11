package vsa;

import entities.Auto;
import entities.Poistenie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        create();
//        readAuto("BA-111-AK");
        for (Poistenie p: readFromYear()) {
            System.out.println("" + p.getVozidlo().getSpz());
        }
    }

    public static void create() {
        
        Auto a = new Auto();
        a.setSpz("BA-111-AK");
        a.setZnacka("Bavorak");
        a.setRokVyroby(1999);
        
        Poistenie p = new Poistenie();
        p.setCislo(111L);
        p.setSuma(159.99);
        p.setVozidlo(a);


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {

            em.persist(p);
//            em.persist(a);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }
    
    static List<Poistenie> readFromYear(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Poistenie> q = em.createQuery("select p from Poistenie p where p.vozidlo.rokVyroby=1999",Poistenie.class);
        return q.getResultList();        
    }
}