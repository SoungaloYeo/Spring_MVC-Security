/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.repositories;

import com.objis.entities.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yeo-sglo
 */
public interface VirementRepository extends JpaRepository<Virement, Long>{
    
}
