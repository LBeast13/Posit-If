package fr.insalyon.dasi.positif.metier.service;

import fr.insalyon.dasi.positif.dao.AstrologueDAO;
import fr.insalyon.dasi.positif.dao.ClientDAO;
import fr.insalyon.dasi.positif.dao.EmployeDAO;
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.dao.TarologueDAO;
import fr.insalyon.dasi.positif.dao.VoyantDAO;
import fr.insalyon.dasi.positif.metier.modele.Astrologue;
import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Conversation;
import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Medium;
import fr.insalyon.dasi.positif.metier.modele.Tarologue;
import fr.insalyon.dasi.positif.metier.modele.Voyant;
import fr.insalyon.dasi.positif.util.Message;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
        
    }
    
    /**
     * Permet l'inscription d'un nouveau Client (Ajout dans la base)
     *
     * @param client Le nouveau client à ajouter
     * @return Vrai si l'inscription à été réalisée
     */
    public boolean sInscrire (Client client){
        JpaUtil.creerEntityManager();
        try{
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

            // Transaction Persistence
            JpaUtil.ouvrirTransaction();
            ClientDAO.creer(client);
            JpaUtil.validerTransaction();
            
            envoiMailInscription(client,0);
            JpaUtil.fermerEntityManager();
            return true;
            
        } catch(Exception e){
            envoiMailInscription(client,1);
            JpaUtil.fermerEntityManager();
            return false;
        }
         
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
        if (client != null && client.getMotDePasse().equals(motDePasse)){
            System.out.println("Vous êtes connecté !");
            return client;
        }
        else {
            System.out.println("Votre email ou votre mot de passe est incorrect !");
            return null;
        }
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
        if (employe != null && employe.getMotDePasse().equals(motDePasse)) {
            return employe;
        } else {
            return null;
        }
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

    /**
     * Simule l'envoi d'un mail de confirmation ou d'erreur dans la console 
     * suite à l'inscription d'un nouveau client.
     * 
     * @param c le nouveau client
     * @param statut pour différencier le message de confirmation (0) et d'erreur(1)
     */
    public void envoiMailInscription(Client c, int statut){
        StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        
        mailWriter.println("Bonjour " + c.getPrenom() +",");
        mailWriter.println();
        
        switch(statut){
            case 0: // Inscription confirmée
                mailWriter.println("  Nous vous confirmons votre inscription au service POSIT'IF.");
                mailWriter.println("  Votre numéro de client est : 4578.");
                break;
                
            case 1: // Erreur d'inscription
                mailWriter.println("  Votre inscription au service POSIT'IF a malencontreusement échoué...");
                mailWriter.println("  Merci de recommencer ultérieurement.");
                break;
        }
        mailWriter.println();
        mailWriter.println("  Cordialement,");
        mailWriter.println();
        mailWriter.println("    L'équipe POSIT'IF");
        
        Message.envoyerMail("contact@posit.if", c.getEmail(), "Bienvenue chez POSIT'IF", corps.toString());
    }
    
    /**
     * Initialisation en "dur" de la liste des mediums et des employés dans 
     * la base de données.
     */
    public static void initialisation()
    {
        JpaUtil.creerEntityManager();

        // Employés Init
        Employe e1 = new Employe(true,"Bette","Liam","toto123","liam.bette@posit.if","0600000001");
        Employe e2 = new Employe(false,"Bosio","Alexis","123456","alexis.bosio@posit.if","0600000002");
        Employe e3 = new Employe(true,"Remy","Thibault","blabli123","thibault.remy@posit.if","0600000003");
        
        // Mediums
        Voyant m1 = new Voyant("Boule de Cristal", "Gwenaël", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.");
        Voyant m2 = new Voyant("Marc de Café", "Professeur Maxwell", "Votre avenir est devant vous : regardons le ensemble !");
        Tarologue m3 = new Tarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Tarologue m4 = new Tarologue("Endora", "Mes cartes répondront à toutes vos questions personnelles.");
        Astrologue m5 = new Astrologue("Serena", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.","École Normale Supérieur d'Astrologie (ENS-Astro)","2006");
        Astrologue m6 = new Astrologue("Mr M. Histaire-Hyeux", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !","Institut des Nouveaux Savoirs Astrologiques","2010");

        // Preparation des listes de mediums et d'employés
        List listeEmp1 = new ArrayList();
        listeEmp1.add(e1);
        listeEmp1.add(e3);
        
        List listeEmp2 = new ArrayList();
        listeEmp2.add(e2);
        listeEmp2.add(e3);
        
        List listeEmp3 = new ArrayList();
        listeEmp3.add(e1);
        listeEmp3.add(e2);
        
        List listeEmp4 = new ArrayList();
        listeEmp3.add(e3);
        
        List listeMed1 = new ArrayList();
        listeMed1.add(m1);
        listeMed1.add(m3);
        listeMed1.add(m6);
        
        List listeMed2 = new ArrayList();
        listeMed2.add(m2);
        listeMed2.add(m3);
        listeMed2.add(m5);
        
        List listeMed3 = new ArrayList();
        listeMed3.add(m1);
        listeMed3.add(m2);
        listeMed3.add(m4);
        listeMed3.add(m5);
        listeMed3.add(m6);
        
        // Ajout des listes
        m1.setEmployes(listeEmp1);
        m2.setEmployes(listeEmp2);
        m3.setEmployes(listeEmp3);
        m4.setEmployes(listeEmp4);
        m5.setEmployes(listeEmp2);
        m6.setEmployes(listeEmp1);
        
        e1.setMediums(listeMed1);
        e2.setMediums(listeMed2);
        e3.setMediums(listeMed3);
        
        JpaUtil.ouvrirTransaction();
        EmployeDAO.creer(e1);
        EmployeDAO.creer(e2);
        EmployeDAO.creer(e3);
        
        VoyantDAO.creer(m1);
        VoyantDAO.creer(m2);
        TarologueDAO.creer(m3);
        TarologueDAO.creer(m4);
        AstrologueDAO.creer(m5);
        AstrologueDAO.creer(m6);
        JpaUtil.validerTransaction();
    }
}
