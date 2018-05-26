/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.repositories;

import com.objis.entities.Client;
import com.objis.entities.CompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yeo-sglo
 */
public interface CompteCourantRepository extends JpaRepository<CompteCourant, Long>{
    
    public CompteCourant findByClient(Client client);
    public CompteCourant findByCode(String str);
    
}
