/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Voyant;
import javax.persistence.EntityManager;

/**
 *
 * @author Liam
 */
public class VoyantDAO extends MediumDAO{
    
    public static void creer(Voyant v) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(v);
    }
}
