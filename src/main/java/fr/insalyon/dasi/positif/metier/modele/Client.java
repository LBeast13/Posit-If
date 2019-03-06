/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Liam
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * La date de naissance du client.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    
    /**
     * L'adresse du client.
     */
    private String adresse;
    
    /**
     * Le numéro de téléphone du client.
     */
    private String numeroTel;
    
    /**
     * Le signe chinois du client.
     */
    private String signeChinois;
    
    /**
     * La couleur porte bonheur du client.
     */
    private String couleur;
    
    /**
     * L'animal totem du client.
     */
    private String animal;

    /**
     * Constructeur par défaut.
     */
    public Client(){ }
    
    /**
     * Constructeur
     * @param dateNaissance La date de naissance du Client
     * @param adresse L'adresse du Client
     * @param numeroTel Le numéro de téléphone du Client
     * @param signeChinois Le signe Chinois du Client
     * @param couleur La couleur porte bonheur du Client
     * @param animal L'animal totem du Client
     */
    public Client(Date dateNaissance, String adresse, String numeroTel, String signeChinois, String couleur, String animal) {
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.numeroTel = numeroTel;
        this.signeChinois = signeChinois;
        this.couleur = couleur;
        this.animal = animal;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.insalyon.dasi.positif.metier.modele.Client[ id=" + id + " ]";
    }
 
//******** GETTERS ET SETTERS *************
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
    
}
