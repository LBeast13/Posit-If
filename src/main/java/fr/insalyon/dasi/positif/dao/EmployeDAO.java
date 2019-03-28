package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Le Data Access Object d'Employé.
 * 
 * @author Liam BETTE, Alexis BOSIO, Thibault REMY
 */
public class EmployeDAO extends PersonneDAO {

    /**
     * Permet d'obtenir tous les Employés de la base de donnée
     * @return la liste de tous les Employés
     */
    public static List<Employe> obtenirTous() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("SELECT e FROM Employe e").getResultList();
    }

    /**
     * Permet de Modifier l'Employé passé en paramètre dans la base de donnée
     * @param e l'Employé à modifier
     */
    public static void modifier(Employe e) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(e);
    }

    /**
     * Permet de Créer un nouvel Employé dans la base de donnée.
     * @param e le nouvel Employé
     */
    public static void creer(Employe e) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(e);
    }

    /**
     * Permet de récupérer l'Employé pouvant incarner le médium passé
     * en paramètre et qui a le moins d'affectations.
     * 
     * @param medium Le médium à incarner
     * @return l'Employé qui va effectuer la voyance
     */
    public static Employe obtenirEmployePourVoyance(Medium medium) {
        EntityManager em = JpaUtil.obtenirEntityManager();

        Query q = em.createQuery("SELECT e FROM Employe e "
                + "WHERE :medium MEMBER OF e.mediums and e.disponible = TRUE");
        q.setParameter("medium", medium);

        List<Employe> empListe = q.getResultList();
        int indexEmp = 0;

        if (!empListe.isEmpty()) {
            int min = 99999;
            int compt = 0;
            
            //Parcours de la liste d'employé pour récupérer celui qui a eu le
            //moins de conversations
            for (Employe e : empListe) {
                if (min > e.getConversations().size()) {
                    min = e.getConversations().size();
                    indexEmp = compt;
                }
                compt++;
            }
        }
        
        return (Employe) empListe.get(indexEmp);

    }
}