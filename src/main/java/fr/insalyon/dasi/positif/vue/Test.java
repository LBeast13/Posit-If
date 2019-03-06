/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.vue;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Client;
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
     * Afficher une personne sur la Console.
     * @param p Entité Personne à afficher
     */
    public static void afficher(Personne p) {

        System.out.println("#" + p.getId() + " " + p.getNom() + " " + p.getPrenom() + " (" + p.getMotDePasse() + ")");
    }
    
    /**
     * Méthode de test: créer des personnes.
     */
    public static void testCreerPersonnes() {
        
        Personne p1 = new Personne("Mentor","Gerard","password1");
        Personne p2 = new Personne("Terieur","Alain","password2");
        Personne p3 = new Personne("Terieur","Alex","password3");

        Service service = new Service();
        
        service.ajouterPersonne(p1);
        service.ajouterPersonne(p2);
        service.ajouterPersonne(p3);

    }
    
    public static void testCreerClients() {
        Calendar calendar1 = new GregorianCalendar(1996,1,30);
        Date dateNais1 =  calendar1.getTime();
        
        Calendar calendar2 = new GregorianCalendar(2000,4,3);
        Date dateNais2 =  calendar2.getTime();
        
        Calendar calendar3 = new GregorianCalendar(2016,3,6);
        Date dateNais3 =  calendar3.getTime();
        
        Client p1 = new Client(dateNais1,"Chine","06254789","chien","bleu","hiboux");
        Client p2 = new Client(dateNais2,"France","06478954","chat","bleu","hiboux");
        Client p3 = new Client(dateNais3,"Espagne","06451278","tigre","bleu","hiboux");

        Service service = new Service();
        
        service.ajouterClient(p1);
        service.ajouterClient(p2);
        service.ajouterClient(p3);
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
        testCreerPersonnes();
        testCreerClients();
        // Libération du JpaUtil
        JpaUtil.destroy();
    }

    

}

