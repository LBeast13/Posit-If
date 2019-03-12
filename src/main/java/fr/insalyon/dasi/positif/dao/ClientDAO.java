/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static javax.swing.text.html.HTML.Tag.SELECT;

/**
 *
 * @author tremy
 */
public class ClientDAO extends PersonneDAO {

    public static List<Client> obtenirTous() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("SELECT c FROM Client c").getResultList();
    }

    public static Client obtenir(String email) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            Query q = em.createQuery("SELECT c FROM Client c WHERE c.email = :email");
            q.setParameter("email", email);
            return (Client) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public static void creer(Client c) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(c);
    }

    public static void modifier(Client c) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(c);
    }

}
