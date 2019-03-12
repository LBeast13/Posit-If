/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Conversation;
import javax.persistence.EntityManager;

/**
 *
 * @author tremy
 */
public class ConversationDAO {

    public static void creer(Conversation c) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(c);
    }

    public static void modifier(Conversation c) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(c);
    }
    
}
