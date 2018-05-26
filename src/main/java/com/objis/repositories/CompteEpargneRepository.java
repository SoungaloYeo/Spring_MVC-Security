/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.repositories;

import com.objis.entities.Client;
import com.objis.entities.CompteEpargne;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yeo-sglo
 */
public interface CompteEpargneRepository extends JpaRepository<CompteEpargne, Long>{
    
    public CompteEpargne findByClient(Client client);
    public CompteEpargne findByCode(String str);
    
}
