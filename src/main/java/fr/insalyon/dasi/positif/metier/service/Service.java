package fr.insalyon.dasi.positif.metier.service;

import fr.insalyon.dasi.positif.dao.ClientDAO;
import fr.insalyon.dasi.positif.dao.EmployeDAO;
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Conversation;
import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Medium;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

    public Service() {
        emf = Persistence.createEntityManagerFactory(jpaU.PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
    public static void Initialisation ()
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        JpaUtil.annulerTransaction();
        JpaUtil.destroy();
        JpaUtil.fermerEntityManager();
        JpaUtil.init();
        JpaUtil.validerTransaction();
    }
    /**
     * Permet d'ajouter un nouveau Client dans la base de donnée
     *
     * @param c Le nouveau client à ajouter
     */
    public boolean sInscrire (Client client){
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("Nom");
        String str = sc.nextLine();
        client.setNom(str);
        
        System.out.println("Prenom");
        str = sc.nextLine();
        client.setPrenom(str);
        
        System.out.println("Numero de Telephone");
        str = sc.nextLine();
        client.setNumeroTel(str);
        
        System.out.println("Email");
        str = sc.nextLine();
        client.setEmail(str);
        
        System.out.println("Date de Naissance au format dd/MM/yyyy");
        str = sc.nextLine();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("dd/MM/yyyy");
        try {
        client.setDateNaissance(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.println("Adresse");
        str = sc.nextLine();
        client.setAdresse(str);
        
        System.out.println("MotDePasse");
        str = sc.nextLine();
        client.setMotDePasse(str);
        return false; 
    }
        
    public void ajouterClient(Client c) {
        tx.begin();
        em.persist(c);
        tx.commit();
    }

    /**
     * Permet d'ajouter un nouvel Employé dans la base de donnée
     *
     * @param e Le nouvel employé à ajouter
     */
    public void ajouterEmploye(Employe e) {
        tx.begin();
        em.persist(e);
        tx.commit();
    }

    /**
     * Recherche le client dans la base de donnée à l'aide de son adresse email
     * et son mot de passe.
     *
     * @param email L'adresse email du client
     * @param motDePasse Le mot de passe du client
     * @return Le Client à condition qu'il existe dans la base.
     */
    public static Client seConnecter(String email, String motDePasse) {
       JpaUtil.creerEntityManager();
      Client client = ClientDAO.obtenir(email);
      JpaUtil.fermerEntityManager();
      if (client != null && client.getMotDePasse().equals(motDePasse))
          return client;
      else 
          return null;
           }
/**
     * Recherche l'employé  dans la base de donnée à l'aide de son adresse email
     * et son mot de passe.
     *
     * @param email L'adresse email de l'employé
     * @param motDePasse Le mot de passe de l'employé
     * @return Le Employé à condition qu'il existe dans la base.
     */
    public static Employe seConnecter(Long ID, String motDePasse) {
       JpaUtil.creerEntityManager();
      Employe employe = EmployeDAO.obtenir(ID);
      JpaUtil.fermerEntityManager();
      if (employe != null && employe.getMotDePasse().equals(motDePasse))
          return employe;
      else 
          return null;
           }
    /**
     * Récupère la liste de tous les Mediums de la base de donnée.
     *
     * @return La liste des Mediums
     */
    public List<Medium> obtenirTousMediums() {
        String jpql = "select m "
                + "from Medium m ";

        Query query = em.createQuery(jpql);

        return (List<Medium>) query.getResultList();
    }

    /**
     * ATTENTION NON TERMINE
     * Créer une conversation qui met en relation un Client et un Medium
     *
     * @param client Le client qui demande la consultation
     * @param medium Le medium avec qui il veut réaliser la consultation
     * @return La conversation et nulle si le médium n'est pas disponible
     */
    public Conversation demanderVoyance(Client client, Medium medium) {

        jpaU.creerEntityManager();
        Employe employe = (Employe) EmployeDAO.obtenirEmployePourVoyance(medium);

        if (employe == null) {
            return null;
        }
        return null;

    }

}
