/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.service;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yeo Sglo Name <your.name at your.org>
 */
@Repository
public interface IVirementService {
    
    public ArrayList<String> listCompte();
    
}
