/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.repositories;

import com.objis.entities.Conseiller;
import com.objis.entities.Users;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yeo-sglo
 */
public interface ConseillerRepository extends JpaRepository<Conseiller, Long>{
    
    public Conseiller findByUsers(Users users);
    
}
