package fr.insalyon.dasi.positif.vue;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Astrologue;
import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Conversation;
import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Medium;
import fr.insalyon.dasi.positif.metier.modele.Personne;
import fr.insalyon.dasi.positif.metier.service.Service;
import fr.insalyon.dasi.positif.util.AstroTest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principale pour les tests.
 *
 * @author DASI Team
 */
public class Test {

    /**
     * Fonction de Test d'inscription
     */
    public static void testerInscription() {
        Service service = new Service();

        Calendar calendar1 = new GregorianCalendar(1996, 1, 30);
        Date dateNais1 = calendar1.getTime();
        Client e1 = new Client("Mentor", "Gerard", "password1", "email1@gmail.com", "0624578675", dateNais1, "Chine");
        Client e2 = new Client("Chirac", "Jacques", "password1", "elysee@gmail.com", "0610203040", dateNais1, "France");

        service.sInscrire(e1);
        service.sInscrire(e2);
    }

    /**
     * Test de la connexion d'un client à POSIT'IF
     */
    public static void testerConnexionClient() {
        // Client inexistant
        Personne e = Service.seConnecter("blabla", "blabla");

        // Client existant mais mauvais mot de passe
        Personne e1 = Service.seConnecter("email1@gmail.com", "blabla");

        // Client existant et bon mot de passe
        Personne e2 = Service.seConnecter("email1@gmail.com", "password1");

        // Employé existant
        Personne e3 = Service.seConnecter("alexis.bosio@posit.if", "123456");
    }

    /**
     * Méthode test pour afficher tous les médiums de la base de données.
     */
    public static void testObtenirTousLesMediums() {
        List<Medium> listeMed = Service.obtenirTousMediums();
        System.out.println("Voici tous les mediums :");
        for (int i = 0; i < listeMed.size(); i++) {
            System.out.println("-" + listeMed.get(i).toString());
        }
    }

    /**
     * Méthode test qui renvoie la liste des mediums interpretable par un
     * employé donné
     */
    public static void testMediumsParEmploye() {
        Personne e = Service.seConnecter("alexis.bosio@posit.if", "123456");
        /*   
        List<Medium> listeMed = Service.getMediums(e);
        System.out.println("Voici de tous les mediums  que l'employé "
                            + e.getPrenom() + e.getNom() + " peut incarner :");
        for(int i=0; i<listeMed.size(); i++){
           System.out.println("-" + listeMed.get(i).toString());
        }*/
    }

    /**
     * Méthode Test de demande de voyance (envoi notification à l'employé)
     */
    public static void testDemandeVoyance() {
        Service service = new Service();
        List<Medium> listeMed = Service.obtenirTousMediums();
        Client c = (Client) Service.seConnecter("email1@gmail.com", "password1");

        service.demanderVoyance(c, listeMed.get(0));
    }

    /**
     * Méthode Test d'acceptation de voyance (envoi notification au client)
     */
    public static void testAccepterDemande() {

    }

    public static void TestAstroTest() {
        System.out.println("\n========== DEBUT TEST ASTROTEST ==========");
        AstroTest astro = new AstroTest();
        //Client c = new Client("Mentor","Gerard","password1","email1@gmail.com","0624578675",new Date(97,05,28),"Chine");
        Client c = (Client) Service.seConnecter("email1@gmail.com", "password1");
        System.out.println("Client initial = " + c);

        List profil = null;
        try {
            profil = astro.getProfil(c.getPrenom(), (java.sql.Date) c.getDateNaissance());
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Profil reçu = " + profil);

        if (profil != null) {
            c.setSigneZodiaque((String) profil.get(0));
            c.setSigneChinois((String) profil.get(1));
            c.setCouleur((String) profil.get(2));
            c.setAnimal((String) profil.get(3));
            System.out.println("Client perfectionné = " + c);
        }

        List predictions = null;
        try {
            predictions = astro.getPredictions(c.getCouleur(), c.getAnimal(), 4, 0, 4);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Prédictions reçues = " + predictions);

        System.out.println("========== FIN TEST ASTROTEST ==========");
    }

    public static void TestServicePrediction() {
        Calendar calendar1 = new GregorianCalendar(1996, 1, 30);
        Date dateNais1 = calendar1.getTime();
        System.out.println("\n========== DEBUT TEST SERVICE PREDICTIONS ==========");
        Client c = new Client("Mentor", "Gerard", "password1", "email1@gmail.com", "0624578675", dateNais1, "Chine");

        List predictions = Service.ObtenirPredictions(c, 4, 0, 4);
        System.out.println("Prédictions reçues = " + predictions);
        System.out.println("========== FIN TEST SERVICE PREDICTIONS ==========");
    }

    public static void TestServiceGraphiques() {
        Calendar calendar1 = new GregorianCalendar(1996, 1, 30);
        Date dateNais1 = calendar1.getTime();
        System.out.println("\n========== DEBUT TEST SERVICE GRAPHIQUES ==========");
        Client c = new Client("Mentor", "Gerard", "password1", "email1@gmail.com", "0624578675", dateNais1, "Chine");
        Service service = new Service();
        service.sInscrire(c);

        List<Medium> mediums = Service.obtenirTousMediums();
        
        Conversation conversation = service.demanderVoyance(c, mediums.get(2));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(2));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(1));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(2));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(2));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(2));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(2));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(3));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(3));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(4));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(4));
        Service.TerminerVoyance(conversation);
        conversation = service.demanderVoyance(c, mediums.get(4));
        Service.TerminerVoyance(conversation);
        System.out.println("\nhistogramme voyances/mediums = " + Service.ObtenirHistogrammeVoyancesParMedium());
        System.out.println("\nhistogramme voyances/employe = " + Service.ObtenirHistogrammeVoyancesParEmploye());
        System.out.println("\ncamembert voyances/employe = " + Service.ObtenirCamembertVoyancesParEmploye());
        System.out.println("========== FIN TEST SERVICE GRAPHIQUES ==========");
    }
 
//     //  ======================
//       ||   DEMONSTRATION  ||
//     *  ======================
//     *
//     */
    
    /**
     * Méthode main(): point d'entrée de ce programme de test.
     *
     * @param args
     */
    public static void main(String[] args) {

        // Initialisation du JpaUtil
        JpaUtil.init();
  Service service = new Service();
    

        System.out.println("\n\n========== INITIALISATION ==========");
        System.out.println("INITIALISATION DE LA BASE DE DONNEES AVEC DES VOYANTS ET DES EMPLOYES");
        Service.initialisation();
        
         Astrologue m8 = new Astrologue("brenda", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.", "École Normale Supérieur d'Astrologie (ENS-Astro)", "2006");
       
        System.out.println("\n\n========== CONNEXIONS D'EMPLOYES ==========");
        System.out.println("CONNEXION DE LIAM :");
        System.out.println("RESULTAT = " + service.seConnecter("liam.bette@posit.if", "toto123"));
        
        System.out.println("\nCONNEXION DE ALEXIS :");
        System.out.println("RESULTAT = " + service.seConnecter("alexis.bosio@posit.if", "123456"));
              
               
        System.out.println("\nCONNEXION DE ZOUHAIR (mauvais identifiant) :");
        System.out.println("RESULTAT = " + service.seConnecter("souhait@csdkn.lef", "GXzr"));
        
        System.out.println("\nCONNEXION DE ZOUHAIR (mauvais mot de passe) :");
        System.out.println("RESULTAT = " + service.seConnecter("alexis.bosio@posit.if", "mauvais mot de passe"));
        
        System.out.println("\n\n========== INSCRIPTION DE EMPLOYES ==========");
        Employe e3 = new Employe(true, "COTE", "Bernard", "bernardo", "bernardo@peinardo.com", "0600000012");
        
        System.out.println("\n\n========== INSCRIPTION DE CLIENTS ==========");
        Client gerard = new Client("Mentor", "Gerard", "password1", "email1@gmail.com", "0624578675", new Date(97,05,28), "Chine");
        System.out.println("INSCRIPTION DE GERARD :");
        System.out.println("RESULTAT = " + service.sInscrire(gerard));
        
         Client claude = new Client("RETU", "Claude", "LTce", "claude.lost@google.com", "0624578675", new Date(85,05,28), "Chine");
        System.out.println("INSCRIPTION DE GERARD :");
        System.out.println("RESULTAT = " + service.sInscrire(claude));
            
        /**/
        
        System.out.println("\n\n========== CONNEXIONS DE CLIENTS ==========");
        System.out.println("CONNEXION DE CLAUDE :");
        Personne claudeConnecte = Service.seConnecter("claude.lost@google.com", "LTce");
        System.out.println("RESULTAT = " + claudeConnecte);
        
        System.out.println("\nCONNEXION DE JEAN :");
        Personne jeanConnecte = Service.seConnecter("jean.neymar@google.com", "NRJn");
        System.out.println("RESULTAT = " + jeanConnecte);
        
        System.out.println("\nCONNEXION DE JEAN (mauvaise adresse) :");
        System.out.println("RESULTAT = " + Service.seConnecter("mauvaise adresse", "NRJn"));
        
        System.out.println("\nCONNEXION DE JEAN (mauvais mot de passe) :");
        System.out.println("RESULTAT = " + Service.seConnecter("jean.neymar@google.com", "mauvais mot de passe"));
        
        
        System.out.println("\n\n========== DEMANDES DINFORMATIONS SUR LES MEDIUMS ==========");       
        System.out.println("\nDEMANDE TOUT LES MEDIUMS :");
        List<Medium>  mediums = Service.obtenirTousMediums();
        System.out.println("RESULTAT = " + mediums);
        
        
        System.out.println("\n\n========== DEMANDES DE VOYANCE ==========");
        System.out.println("CLAUDE DEMANDE MEDIUM DU JOUR :");
        Conversation conversationClaude = service.demanderVoyance (claude, mediums.get(0) );
        Employe employeConversationClaude = conversationClaude.getEmploye();
        System.out.println("RESULTAT = " + conversationClaude + '\n'); 
        
        System.out.println("\n\n========== ACCEPTATION DE VOYANCE ==========");
        System.out.println("ACCEPTATION DE LA VOYANCE DE CLAUDE :");
        
        System.out.println("RESULTAT = " + conversationClaude);
        
        
        System.out.println("\n\n========== DEMANDE DE PREDICTIONS POUR UN CLIENT ==========");
        System.out.println("DEMANDE DE PREDICTIONS POUR CLAUDE :");
        System.out.println("RESULTAT = " + Service.ObtenirPredictions(conversationClaude.getClient(), 2, 0, 4));
              
        
        System.out.println("\n\n========== FIN DE VOYANCE ==========");
        System.out.println("Attente de 2 secondes...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.hashCode();
        }
        System.out.println("FIN DE LA VOYANCE DE CLAUDE (" + new Date() +") :");
        Service.TerminerVoyance(conversationClaude);
        System.out.println("RESULTAT = " + conversationClaude);
        
        System.out.println("\nAttente de 2 secondes...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.hashCode();
        }
        System.out.println("FIN DE LA VOYANCE DE CLAUDE (" + new Date() +") :");
        Service.TerminerVoyance(conversationClaude);
        System.out.println("RESULTAT = " + conversationClaude);
        
        
           
        
        System.out.println("\n\n========== COMMENTER UNE VOYANCE ==========");
        System.out.println("COMMENTER LA VOYANCE De CLAUDE :");
        Service.CommenterVoyance(conversationClaude,"CLAUDE est complexé par la petite taille de son phalus.");
        System.out.println("RESULTAT = " + conversationClaude);
        
        
        System.out.println("\n\n========== DEMANDER LES STATISTIQUES ==========");
        System.out.println("DEMANDER L'HISTOGRAMME DE VOYANCES PAR MEDIUM :");
        System.out.println("RESULTAT = " + Service.ObtenirHistogrammeVoyancesParMedium());
        
        System.out.println("\nDEMANDER L'HISTOGRAMME DE VOYANCES PAR EMPLOYE :");
        System.out.println("RESULTAT = " + Service.ObtenirHistogrammeVoyancesParEmploye());
        
        System.out.println("\nDEMANDER LE CAMEMBERT DE VOYANCES PAR EMPLOYE :");
        System.out.println("RESULTAT = " + Service.ObtenirCamembertVoyancesParEmploye());
        
        System.out.println("\n\n");
        
       
    
        //Initialisation des employés et des médiums
        //Service.initialisation();

        // Ici, appel des différentes méthodes de test
        // Mettre/Enlever les commentaires pour réaliser une série de test
        // S'assurer que les tables sont bien remplies avant de tester !
        //testerInscription();                //OK
        //testerConnexionClient();          //OK
        //testObtenirTousLesMediums();      //OK
        //testDemandeVoyance();
        //TestAstroTest();
        //TestServicePrediction();            // OK
        //TestServiceGraphiques(); //ok

        // Libération du JpaUtil
        JpaUtil.destroy();
    }

}
