package fr.insalyon.dasi.positif.vue;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Personne;
import fr.insalyon.dasi.positif.metier.service.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Classe principale pour les tests.
 * @author DASI Team
 */
public class Test {

    /**
     * Afficher une personne sur la Console.
     * @param p Entité Personne à afficher
     */
    public static void afficher(Personne p) {

        System.out.println("#" + p.getId() + " " + p.getNom() + " " + p.getPrenom() + " (" + p.getMotDePasse() + ")");
    }
    
    
    /**
     * Fonction de Test d'inscription
     */
    public static void testerInscription(){
        Service service = new Service();
        
        Client e = new Client();
        service.sInscrire(e);

 
        Calendar calendar1 = new GregorianCalendar(1996,1,30);
        Date dateNais1 =  calendar1.getTime();
        Client e1 = new Client("Mentor","Gerard","password1","email1@gmail.com","0624578675",dateNais1,"Chine","Tigre","Bleu","Hiboux");
         Client e2 = new Client("Chirac","Jacques","password1","elysee@gmail.com","0610203040",dateNais1,"France","Lyon","Bleu","Hiboux");
         
                 service.sInscrire(e1);
        service.sInscrire(e2);
    }
    
    /**
     * Test de la connexion d'un client à POSIT'IF
     */
    public static void testerConnexionClient(){
        // Client inexistant
        Personne e = Service.seConnecter("blabla", "blabla");
        
        // Client existant mais mauvais mot de passe
        Personne e1 = Service.seConnecter("jean.dujardin@gmail.com", "blabla");
        
        // Client existant et bon mot de passe
        Personne e2 = Service.seConnecter("jean.dujardin@gmail.com", "jdujardin123");
        
        Personne e3 = Service.seConnecter("alexis.bosio@posit.if", "123456");
    }
    
    /**
     * Méthode main(): point d'entrée de ce programme de test.
     * @param args 
     */
    public static void main(String[] args) {

        // Initialisation du JpaUtil
        JpaUtil.init();
        
        //Initialisation des employés et des médiums
       // Service.initialisation();
        
        // Ici, appel des différentes méthodes de test
        // Mettre/Enlever les commentaires pour réaliser une série de test
        
        //testerInscription();
        //testerConnexionClient();
       
        // Libération du JpaUtil
        JpaUtil.destroy();
    }

    

}

