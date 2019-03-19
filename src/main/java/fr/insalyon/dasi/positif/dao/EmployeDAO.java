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
    
    public static void creer(Employe e) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(e);
    }

    public static Employe obtenirEmployePourVoyance(Medium medium) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("SELECT e FROM Employe e "
                + "WHERE :medium MEMBER OF e.mediums and e.disponible = TRUE");
        q.setParameter("medium", medium);
        List l = q.getResultList();

        if (l.isEmpty()) {
            return null;
        } else {
            return (Employe) l.get(0);
        }

    }
}
