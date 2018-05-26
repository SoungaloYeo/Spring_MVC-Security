/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author yeo-sglo
 */
@Entity
public class Conseiller extends Personne {

    @Transient
    private final String PREFIX = "CONS-000";
    private String codeConseiller;

    @OneToMany(mappedBy = "conseiller")
    private List<Client> listeClient;

    @OneToOne
    private Users users;

    public String getCodeConseiller() {
        return codeConseiller;
    }

    public void setCodeConseiller() {
        this.codeConseiller = PREFIX + "" + getId();
    }

    public List<Client> getListeClient() {
        return listeClient;
    }

    public void setListeClient(List<Client> listeClient) {
        this.listeClient = listeClient;
    }

    public Conseiller() {
    }

}
