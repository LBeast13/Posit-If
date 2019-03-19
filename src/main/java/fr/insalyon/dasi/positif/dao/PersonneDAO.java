/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Personne;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tremy
 */
public class PersonneDAO {
    
    
    public static Personne obtenir(String email) {
         EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                                   + "FROM Personne p "
                                   + "WHERE p.email = :email");
            q.setParameter("email", email);
            return (Personne) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
