/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import entities.Kniha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        create();
    }
    static void create() {
        Kniha k = new Kniha();
        k.setId(333L);
        k.setNazov("Jazyk C");
        k.setAutori(new ArrayList<>());
        k.getAutori().add("Kernighan");
        k.getAutori().add("Ritchey");
        
        persist(k);
    }

    static void persist(Object object) {
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
