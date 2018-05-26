/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author yeo-sglo
 */

@MappedSuperclass
public abstract class Compte implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idCompte;
    protected double solde;

    public Long getIdCompte() {
        return idCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public boolean retrait(double montant){
        if(solde > montant){
            solde = solde - montant;
            return true;
        }else{
            return false;
        }        
    }

    protected Compte() {
    }

    protected Compte(double solde) {
        this.solde = solde;
    }

}
