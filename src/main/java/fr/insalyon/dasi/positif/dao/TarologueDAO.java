/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Tarologue;
import javax.persistence.EntityManager;

/**
 *
 * @author Liam
 */
public class TarologueDAO extends MediumDAO {
    
    public static void creer(Tarologue t) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(t);
    }
}
