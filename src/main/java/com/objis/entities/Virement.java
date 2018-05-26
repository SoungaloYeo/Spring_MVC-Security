/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author yeo-sglo
 */
@Entity
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeCpteEmetteur;
    private String codeCpteRecepteur;
    private double montant;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCodeCpteEmetteur() {
        return codeCpteEmetteur;
    }

    public void setCodeCpteEmetteur(String codeCpteEmetteur) {
        this.codeCpteEmetteur = codeCpteEmetteur;
    }

    public String getCodeCpteRecepteur() {
        return codeCpteRecepteur;
    }

    public void setCodeCpteRecepteur(String codeCpteRecepteur) {
        this.codeCpteRecepteur = codeCpteRecepteur;
    }

    public Virement() {
    }

    public Virement( String codeCpteEmetteur, String codeCpteRecepteur, double montant, Date date) {
        this.codeCpteEmetteur = codeCpteEmetteur;
        this.codeCpteRecepteur = codeCpteRecepteur;
        this.montant = montant;
        this.date = date;
    }
       
}
