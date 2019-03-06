/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * Classe représentant un astrologue.
 * Super classe : Medium
 * @author Liam BETTE, Alexis BOSIO, Thibault REMY
 */
@Entity
@DiscriminatorValue("A")
public class Astrologue extends Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * La formation de l'astrologue.
     */
    private String formation;
    
    /**
     * La promotion de l'astrologue.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date promotion;

    /**
     * Constructeur par défaut
     */
    public Astrologue() {
    }

    /**
     * Constructeur
     * @param nom Le nom de l'astrologue
     * @param descriptif Le descriptif de l'astrologue
     * @param promotion La date de promotion de l'astrologue
     */
    public Astrologue(String nom, String descriptif, Date promotion) {
        super(nom, descriptif);
        this.promotion = promotion;
    }
    
/*********** GETTERS ET SETTERS *************/
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Astrologue)) {
            return false;
        }
        Astrologue other = (Astrologue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.insalyon.dasi.positif.metier.modele.Astrologue[ id=" + id + " ]";
    }
    
}