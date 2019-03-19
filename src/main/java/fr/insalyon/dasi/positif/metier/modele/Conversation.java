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
 * Classe représentant une Conversation.
 * @author Liam BETTE, Alexis BOSIO, Thibault REMY
 */
@Entity
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * Date de début de la conversation
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date debut;
    
    /**
     * Date de fin de la conversation
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fin;
    
    /**
     * Commentaire de la conversation
     */
    private String commentaire;
    private Employe employe;
    private Medium medium;
    private Client client;

    /**
     * Constructeur par défaut
     */
    public Conversation(){
        
    }
    
    /**
     * Constructeur
     * @param employe employe realisant conversation
     * @param medium medium joué par employe
     * @param client Client realisant conversation 
     */
    public Conversation(Employe employe ,Medium medium, Client client) {
        this.employe = employe;
        this.medium = medium;
        this.client = client;
        client.getConversations();
        employe.getConversation(); 
        medium.getConversation();
        
    }
    
//******** GETTERS ET SETTERS *************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
        if (!(object instanceof Conversation)) {
            return false;
        }
        Conversation other = (Conversation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.insalyon.dasi.positif.metier.modele.Conversation[ id=" + id + " ]";
    }
}
