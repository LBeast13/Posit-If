/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.vue;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.modele.Personne;
import fr.insalyon.dasi.positif.metier.service.Service;
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

    /**
     * Méthode main(): point d'entrée de ce programme de test.
     * @param args 
     */
    public static void main(String[] args) {

        // Initialisation du JpaUtil
        JpaUtil.init();

        // Ici, appel des différentes méthodes de test
        // Mettre/Enlever les commentaires pour réaliser une série de test
        
        // Libération du JpaUtil
        JpaUtil.destroy();
    }

}

