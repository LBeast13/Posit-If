/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tremy
 */
public class EmployeDAO extends PersonneDAO {

    public static List<Employe> obtenirTous() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("SELECT e FROM Employe e").getResultList();
    }

    public static void modifier(Employe e) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(e);
    }

    public static void creer(Employe e) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(e);
    }

    public static Employe obtenirEmployePourVoyance(Medium medium) {
        EntityManager em = JpaUtil.obtenirEntityManager();
<<<<<<< .mine        Query q = em.createQuery("SELECT e FROM Employe e  "
=======
        Query q = em.createQuery("SELECT e FROM Employe e "
>>>>>>> .theirs                + "WHERE :medium MEMBER OF e.mediums and e.disponible = TRUE");
        q.setParameter("medium", medium);

        List<Employe> l = q.getResultList();

        if (l.isEmpty()) {
            return null;
        } else {
            int min = 99999;
            int i = 0;
            int j = 0;
            for (Employe e : l) {
                if (min > e.getConversations().size()) {
                    min = e.getConversations().size();

                    i = j;

                }
                j++;
            }
        
            return (Employe) l.get(i);
        }

    }
}
