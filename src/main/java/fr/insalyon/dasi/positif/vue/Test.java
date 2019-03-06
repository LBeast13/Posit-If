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
    
    public static void testerCreerEmployes(){
        Employe e1 = new Employe(true,"Mentor","Gerard","password1","email1@gmail.com","0624578675");
        Employe e2 = new Employe(false,"Terieur","Alain","password2","email2@gmail.com","0624578675");
        Employe e3 = new Employe(true,"Terieur","Alex","password3","email3@gmail.com","0624578675");

        Service service = new Service();
        
        service.ajouterEmploye(e1);
        service.ajouterEmploye(e2);
        service.ajouterEmploye(e3);
    }
    
    public static void testerCreerClients(){
        Calendar calendar1 = new GregorianCalendar(1996,1,30);
        Date dateNais1 =  calendar1.getTime();
        
        Calendar calendar2 = new GregorianCalendar(2000,4,3);
        Date dateNais2 =  calendar2.getTime();
        
        Calendar calendar3 = new GregorianCalendar(2016,3,6);
        Date dateNais3 =  calendar3.getTime();
        
        Client e1 = new Client("Mentor","Gerard","password1","email1@gmail.com","0624578675",dateNais1,"Chine","Tigre","Bleu","Hiboux");
        Client e2 = new Client("Terieur","Alain","password2","email2@gmail.com","0624578675",dateNais2,"France","Tigre","Bleu","Hiboux");
        Client e3 = new Client("Terieur","Alex","password3","email3@gmail.com","0624578675",dateNais3,"Espagne","Tigre","Bleu","Hiboux");

        Service service = new Service();
        
        service.ajouterClient(e1);
        service.ajouterClient(e2);
        service.ajouterClient(e3);
    }
    
    /**
     * Méthode main(): point d'entrée de ce programme de test.
     * @param args 
     */
    public static void main(String[] args) {

        // Initialisation du JpaUtil
        JpaUtil.init();

        // Ici, appel des différentes méthodes de test
        // Mettre/Enlever les commentaires pour réaliser une série de test
        testerCreerEmployes();
       
        // Libération du JpaUtil
        JpaUtil.destroy();
    }

    

}
