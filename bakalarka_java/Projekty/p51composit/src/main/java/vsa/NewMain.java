package vsa;

import entities.Kosik;
import entities.Polozka;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;

public class NewMain {
    public static void main(String[] args) {
        create();
//        removeKosik();
//        removePolozka();
    }
    
    public static void create() {
        Kosik k = new Kosik();
        k.setId(333L);
        
        Polozka p1 = new Polozka();
        p1.setTovar("kofola");
        p1.setMnozstvo(1);
        
        Polozka p2 = new Polozka();
        p2.setTovar("treska");
        p2.setMnozstvo(1);
        
        Polozka p3 = new Polozka();
        p3.setTovar("rozok");
        p3.setMnozstvo(2);
        
        k.setPolozka(new ArrayList<>());
        k.getPolozka().add(p1);
        k.getPolozka().add(p2);
        k.getPolozka().add(p3);
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            
            em.persist(k);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    

    public static void removeKosik() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        
        Kosik k = em.find(Kosik.class, 333L);
        System.out.println(k.getPolozka().size());
        
        em.getTransaction().begin();
        try {
            
            em.remove(k);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }                
        
    }

    
    public static void removePolozka() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        
        Kosik k = em.find(Kosik.class, 333L);
        for (Polozka p: k.getPolozka()) {
            System.out.println(p.getTovar());
        }
        
        em.getTransaction().begin();
        try {
            System.out.println("... removing " + k.getPolozka().get(0));
            k.getPolozka().remove(0);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }        
        
        for (Polozka p: k.getPolozka()) {
            System.out.println(p.getTovar());
        }        
        
    }
    
}
