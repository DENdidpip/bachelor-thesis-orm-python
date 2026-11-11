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
        Firma f = new Firma();
        f.setAdresa("Veda");

        Kniha k1 = new Kniha();
        k1.setNazov("1984");
        Kniha k2 = new Kniha();
        k2.setNazov("Animal Farm");

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

}
