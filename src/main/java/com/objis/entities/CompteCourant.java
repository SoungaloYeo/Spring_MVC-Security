/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author yeo-sglo
 */
@Entity
public class CompteCourant extends Compte{ 
    @Transient
    private final String PREFIX="CC-000";
    
    private String code;
    
    @OneToOne
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCode() {
        return code;
    }

    public void setCode() {
        this.code = PREFIX+""+getIdCompte();
    }

    public CompteCourant() {
    }

    public CompteCourant(String codeCourant, Client client, double solde) {
        super(solde);
        this.code = codeCourant;
        this.client = client;
    }
    
}
