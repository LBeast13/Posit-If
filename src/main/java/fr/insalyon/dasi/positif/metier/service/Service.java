/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier.service;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Client;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import fr.insalyon.dasi.positif.metier.modele.Personne;

/**
 *
 * @author Liam
 */
public class Service {
    
    //Entity Manager, Factory et Transaction (communication DB)
    JpaUtil jpaU = new JpaUtil();
    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;
    
    public Service(){
        emf = Persistence.createEntityManagerFactory(jpaU.PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
        
    public void ajouterPersonne(Personne p){
        tx.begin();
        em.persist(p);
        tx.commit();
    }
    
    public void ajouterClient(Client c){
        tx.begin();
        em.persist(c);
        tx.commit();
    }
}
