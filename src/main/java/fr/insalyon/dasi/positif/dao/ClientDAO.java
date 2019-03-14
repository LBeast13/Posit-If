package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.util.Message;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import static javax.swing.text.html.HTML.Tag.SELECT;

/**
 *
 * @author Liam BETTE, Alexis BOSIO, Thibault REMY
 */
public class ClientDAO extends PersonneDAO {

    /**
     * Permet d'obtenir la liste de tous les clients de la base de données
     * @return  la liste de tous les clients.
     */
    public static List<Client> obtenirTous() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("SELECT c FROM Client c").getResultList();
    }

    /**
     * Recherche un client dans la base de donnée à partir de son email
     * @param email
     * @return Le client recherché
     */
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

    /**
     * Crée un nouveau client dans la base de données
     * @param c le client à créer
     */
    public static void creer(Client c) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(c);
    }

    public static void modifier(Client c) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(c);
    }

}
