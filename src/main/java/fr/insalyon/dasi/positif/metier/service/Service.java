/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier.service;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Medium;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import fr.insalyon.dasi.positif.metier.modele.Personne;
import java.util.List;

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
    
    /**
     * Permet d'inscrire un client en l'ajoutant à la base de donnée
     * @param c 
     */
    public void sInscrire(Client c){
        tx.begin();
        em.persist(c);
        tx.commit();
    }
    
    /**
     * Recherche le client dans la base de donnée à l'aide de son
     * adresse email et son mot de passe.
     * @param email L'adresse email du client
     * @param motDePasse Le mot de passe du client
     * @return Le Client à condition qu'il existe dans la base.
     */
    public Client seConnecter(String email, String motDePasse){
        String jpql = "select c "
                    + "from Client c "
                    + "where c.email='" + email + "'"
                    + "and c.motDePasse='" + motDePasse + "'";
        
        Query query = em.createQuery(jpql);
        List<Client> res = (List<Client>) query.getResultList();
        
        return res.get(0);
    }
    
    /**
     * Récupère la liste de tous les Mediums de la base de donnée.
     * @return La liste des Mediums
     */
    public List<Medium> obtenirTousMediums(){
        String jpql = "select m "
                    + "from Medium m ";
        
        Query query = em.createQuery(jpql);
        
        return (List<Medium>) query.getResultList();
    }
    
    public void ajouterClient(Client c){
        tx.begin();
        em.persist(c);
        tx.commit();
    }
}
