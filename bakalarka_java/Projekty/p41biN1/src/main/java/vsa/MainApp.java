/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import entities.Firma;
import entities.Kniha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        create(); 
//        System.out.println("\ncitanie knihy");
//        readKniha(201L);             
//        System.out.println("\ncitanie firmy");
//        readFirma(101L);             
    }

    public static void create() {
        Firma f = new Firma();
        f.setAdresa("Veda");
        f.setId(101L);

        Kniha k1 = new Kniha();
        k1.setNazov("1984");
        k1.setId(201L);
        Kniha k2 = new Kniha();
        k2.setNazov("Animal Farm");

        k1.setVydavatel(f);
        k2.setVydavatel(f);
        // pre vytvorenie FK v db nie je nutne - skuste zakomentovat nasledujuce 4 riadky
        List<Kniha> kl = new ArrayList<>();
        kl.add(k1);
        kl.add(k2);
        f.setPublikacie(kl);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {

            em.persist(k1);
            em.persist(k2);
            em.persist(f);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void readKniha(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        Kniha k = em.find(Kniha.class, id);
        System.out.println("Nazov knihy: " + k.getNazov());
//        System.out.println("Adresa vydavatela: " + k.getVydavatel().getAdresa());
    }
    
    public static void readFirma(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        Firma f = em.find(Firma.class, id);
        System.out.println("Adresa firmy: " + f.getAdresa());
//        System.out.println("Pocet publikacii: " + f.getPublikacie().size());
    }

}
