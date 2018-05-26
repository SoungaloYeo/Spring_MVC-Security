/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Yeo Sglo Name <your.name at your.org>
 */
@Controller
@RequestMapping(value = "/proxibanque")
public class ConseillerController {

    @RequestMapping(value = "/profil", method = RequestMethod.GET)
    public String profil(Model model) {
        return "profil";
    }

}
