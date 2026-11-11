package vsa;

import entities.Auto;
import entities.Poistenie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainApp {

    public static void main(String[] args) {
        create();
        readAuto("BA-111-AK");
    }

    public static void create() {
        
        Auto a = new Auto();
        a.setSpz("BA-111-AK");
        a.setZnacka("Bavorak");
        
        Poistenie p = new Poistenie();
        p.setCislo(111L);
        p.setSuma(159.99);
        p.setVozidlo(a);
        a.setPzp(p);          // pre DB nie je nutne zadat  

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(p);
            em.persist(a);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }
    
    static void readAuto(String spz){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        Auto a = em.find(Auto.class, spz);
        System.out.println("" + a.getZnacka() + " " + a.getPzp().getCislo());        
    }
}