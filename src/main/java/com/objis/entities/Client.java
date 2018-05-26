/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author yeo-sglo
 */
@Entity
public class Client extends Personne {
    
    @Transient
    private final String PREFIX="CLT-000";
    
    private String codeClient;
    @ManyToOne
    private Conseiller conseiller;

    public Conseiller getConseiller() {
        return conseiller;
    }

    public void setConseiller(Conseiller conseiller) {
        this.conseiller = conseiller;
    }

    public String getCode_client() {
        return codeClient;
    }

    public void setCode_client() {
        this.codeClient = PREFIX+""+getId();
    }
   
    public Client() {
        super();
    }

    public Client(String nom, String prenom, String email, String adresse, String code_client) {
        super(nom, prenom, email, adresse);
        this.codeClient = code_client;
    }

    }
