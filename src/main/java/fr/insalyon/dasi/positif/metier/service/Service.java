package fr.insalyon.dasi.positif.metier.service;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Conversation;
import fr.insalyon.dasi.positif.metier.modele.Employe;
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
 * @author Liam BETTE, Alexis BOSIO, Thibault REMY
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
     * Permet d'ajouter un nouveau Client dans la base de donnée
     * @param c Le nouveau client à ajouter
     */
    public void ajouterClient(Client c){
        tx.begin();
        em.persist(c);
        tx.commit();
    }
    
    /**
     * Permet d'ajouter un nouvel Employé dans la base de donnée
     * @param e Le nouvel employé à ajouter
     */
    public void ajouterEmploye(Employe e){
        tx.begin();
        em.persist(e);
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
    
    /* TODO
    public Conversation demanderVoyance(Client client, Medium medium){
       
    }*/
}
