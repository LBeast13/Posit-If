/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;


import fr.insalyon.dasi.positif.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author tremy
 */
public class MediumDAO {

    /**
     * Récupère la liste de tous les Mediums dans la base de données
     * @return La liste des Mediums
     */
    public static List<Medium> obtenirTous() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("SELECT m FROM Medium m").getResultList();
    }

    public static void modifier(Medium m) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(m);
    }
}
