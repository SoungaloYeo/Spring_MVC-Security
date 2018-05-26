/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.controllers;

import com.objis.entities.Conseiller;
import com.objis.entities.Users;
import com.objis.repositories.ConseillerRepository;
import com.objis.repositories.UsersRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Yeo Sglo Name <your.name at your.org>
 */
@Controller
@SessionAttributes("myUser")
public class SecurityController {
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    ConseillerRepository conseillerRepository;

    @RequestMapping(value = "/")
    public String racine(Model model, HttpServletRequest request) {
        String user = request.getRemoteUser();
        Users myUser = usersRepository.findByUsername(user);        
        model.addAttribute("myUser", myUser);
        
        return "redirect:/proxibanque/clients";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    
//     @RequestMapping(value = "/403")
//    public String accessDenied() {
//        return "403";
//    }

}
