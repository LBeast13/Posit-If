package fr.insalyon.dasi.positif.vue;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.dao.MediumDAO;
import fr.insalyon.dasi.positif.metier.modele.Client;
import fr.insalyon.dasi.positif.metier.modele.Employe;
import fr.insalyon.dasi.positif.metier.modele.Medium;
import fr.insalyon.dasi.positif.metier.modele.Personne;
import fr.insalyon.dasi.positif.metier.service.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Classe principale pour les tests.
 * @author DASI Team
 */
public class Test {
    
    /**
     * Fonction de Test d'inscription
     */
    public static void testerInscription(){
        Service service = new Service();
       
        Calendar calendar1 = new GregorianCalendar(1996,1,30);
        Date dateNais1 =  calendar1.getTime();
        Client e1 = new Client("Mentor","Gerard","password1","email1@gmail.com","0624578675",dateNais1,"Chine");
        Client e2 = new Client("Chirac","Jacques","password1","elysee@gmail.com","0610203040",dateNais1,"France");
         
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
        
        // Employé existant
        Personne e3 = Service.seConnecter("alexis.bosio@posit.if", "123456");
    }

   
    /**
     * Méthode test pour afficher tous les médiums de la base de données.
     */
    public static void obtenirTousLesMediums(){ 
        List<Medium> listeMed = Service.obtenirTousMediums();
        System.out.println("Voici tous les mediums : \n");
        for(int i=0; i<listeMed.size(); i++){
           System.out.println("-" + listeMed.get(i).toString());
        }
        
    }
    
     public static void obtenirTousLesClients(){
        
    }
    
    /**
     * Méthode main(): point d'entrée de ce programme de test.
     * @param args 
     */
    public static void main(String[] args) {

        // Initialisation du JpaUtil
        JpaUtil.init();
        
        //Initialisation des employés et des médiums
        //Service.initialisation();
        
        // Ici, appel des différentes méthodes de test
        // Mettre/Enlever les commentaires pour réaliser une série de test
        obtenirTousLesMediums();
        //testerInscription();
        //testerConnexionClient();
       
        // Libération du JpaUtil
        JpaUtil.destroy();
    }

    

}

