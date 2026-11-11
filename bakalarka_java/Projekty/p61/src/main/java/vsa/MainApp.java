package vsa;

import entities.Element;
import entities.RectElement;
import entities.TextElement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        create();
        findTextElement(2L);
        findElement(2L);
        jpqlAllElements();
        jpqlRectElements();
    }

    public static void create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Element elem = new Element();
        elem.setId(1L);
        elem.setName("An element");

        TextElement te = new TextElement();
        te.setId(2L);
        te.setName("A text element");
        te.setText("hello");
        te.setFont("Times New Roman");
        te.setFontSize(16);

        RectElement re = new RectElement();
        re.setId(3L);
        re.setName("A rectangle element");
        re.setHight(100);
        re.setWidth(200);

//        em.persist(elem);
        em.persist(te);
        em.persist(re);

        tx.commit();
        em.close();
        emf.close();
    }

    // 1. vyhladanie objektu podla kluca ak triedu objektu pozname
    static void findTextElement(long id) {
        System.out.println("\n-- find text element --------------");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        TextElement e = em.find(TextElement.class, id);
        System.out.println("\t==> " + e.getText());

        em.close();
        emf.close();
    }

    // 2. vyhladanie objektu podla kluca ak triedu objektu nepozname
    static void findElement(long id) {
        System.out.println("\n-- find element ---------------");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        Element e = em.find(Element.class, id);
        System.out.println(e);
        // skusme ci je to byt TextElement
        if (e instanceof TextElement) {
            System.out.println("\t==> " +  ((TextElement) e).getText());
        }

        em.close();
        emf.close();
    }

    // 4. nacitanie vsetkych udajov zo vsetkych typov objektov
    public static void jpqlAllElements() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        System.out.println("\n-- read all elements --------------");

        Query q3 = em.createQuery("select e from Element e"); // JPQL"
        List<Element> textelementlist = q3.getResultList();
        for (Element e : textelementlist) {
            System.out.println("\t==> " + e);
            System.out.println("\t==> " + e.getClass().toString());
        }

        em.close();
        emf.close();
    }

    public static void jpqlRectElements() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        System.out.println("\n-- read all Rect elements ----------");

        Query q3 = em.createQuery("select e from RectElement e"); // JPQL"
        List<Element> textelementlist = q3.getResultList();
        for (Element e : textelementlist) {
            System.out.println("\t==> " + e);
            System.out.println("\t==> " + e.getClass().toString());
        }

        em.close();
        emf.close();
    }
}
