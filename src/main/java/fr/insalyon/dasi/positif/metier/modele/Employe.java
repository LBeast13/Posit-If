/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier.modele;

import java.io.Serializable;
import static java.util.Collections.list;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe représentant un employé. Super classe : Personne.
 *
 * @author Liam BETTE, Alexis BOSIO, Thibault REMY
 */
@Entity
@DiscriminatorValue("E")
public class Employe extends Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private List<Medium> mediums;
    /**
     * La disponibilité de l'employé.
     */
    private boolean dispo;

    /**
     * Constructeur par défaut
     */
    public Employe() {
        super();
    }

    /**
     * Constructeur
     *
     * @param dispo Disponibilité de l'employé
     * @param nom Nom de l'employé
     * @param prenom Prénom de l'employé
     * @param motDePasse Mot de passe de l'employé
     * @param email Adresse email de l'employé
     * @param numeroTel Numéro de téléphone de l'employé
     */
    public Employe(boolean dispo, String nom, String prenom, String motDePasse, String email, String numeroTel) {
        super(nom, prenom, motDePasse, email, numeroTel);
        this.dispo = dispo;
    }

    /**
     * **** GETTERS ET SETTERS **********
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDispo() {
        return dispo;
    }

    public List<Medium> getMedium() {
        return mediums;
    }

    /**
     * @param mediums que l'employé peut interpréter.
     */
    public void setMediums(List<Medium> mediums) {
        this.mediums = mediums;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.insalyon.dasi.positif.metier.modele.Employe[ id=" + id + " ]";
    }
}
