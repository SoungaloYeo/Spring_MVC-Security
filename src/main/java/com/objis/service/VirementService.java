/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.service;

import com.objis.entities.CompteCourant;
import com.objis.entities.CompteEpargne;
import com.objis.repositories.CompteCourantRepository;
import com.objis.repositories.CompteEpargneRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yeo Sglo Name <your.name at your.org>
 */
@Service
public class VirementService implements IVirementService{
    
    @Autowired
    CompteEpargneRepository compteEpargneRepository;
    
    @Autowired
    CompteCourantRepository compteCourantRepository;
    
    public ArrayList<String> listCompte(){
        ArrayList<String> str = new ArrayList<>();
        for (CompteEpargne compteEpargne : compteEpargneRepository.findAll()) {
            str.add(compteEpargne.getCode());
        }
        for (CompteCourant compteCourant : compteCourantRepository.findAll()) {
            str.add(compteCourant.getCode());
        }
        return str;
    }
    
}
