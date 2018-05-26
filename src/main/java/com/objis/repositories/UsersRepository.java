/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.repositories;

import com.objis.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Yeo Sglo Name <your.name at your.org>
 */
public interface UsersRepository extends JpaRepository<Users, String>{
    
    public Users findByUsername(String str);
    
}
