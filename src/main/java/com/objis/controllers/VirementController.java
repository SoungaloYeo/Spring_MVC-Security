/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.controllers;

import com.objis.entities.CompteCourant;
import com.objis.entities.CompteEpargne;
import com.objis.entities.Users;
import com.objis.repositories.ClientRepository;
import com.objis.repositories.CompteCourantRepository;
import com.objis.repositories.CompteEpargneRepository;
import com.objis.repositories.UsersRepository;
import com.objis.service.IVirementService;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author yeo-sglo
 */
@RequestMapping(value = "/proxibanque")
@Controller
public class VirementController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CompteCourantRepository compteCourantRepository;

    @Autowired
    CompteEpargneRepository compteEpargneRepository;
    
    @Autowired
    IVirementService iVirementService;
    
    @Autowired
    UsersRepository usersRepository;
    
    public void getUser(Model model, HttpServletRequest request) {
        String user = request.getRemoteUser();
        Users myUser = usersRepository.findByUsername(user);        
        model.addAttribute("myUser", myUser);
    }

    @RequestMapping(value = "/virement", method = RequestMethod.GET)
    public String saisirComptes(Model model, HttpSession session, HttpServletRequest request) {
        Collection<String> myList = iVirementService.listCompte(); 
        getUser(model, request);
        model.addAttribute("myList", myList);
        model.addAttribute("clientEmeteur", null);
        model.addAttribute("clientRecepteur", null);
        return "virement";
    }

    @RequestMapping(value = "data-comptes", method = RequestMethod.GET)
    public String dataComptes(
            @RequestParam(value = "selectEmet", defaultValue = "") String cpteE,
            @RequestParam(value = "selectRecep", defaultValue = "") String cpteR,
            @RequestParam(value = "btn-detail", defaultValue = "") String btnDetail,
            @RequestParam(value = "btn-virement", defaultValue = "") String btnVirement,
            @RequestParam(value = "code", defaultValue = "") String codeE,
            @RequestParam(value = "code", defaultValue = "") String codeR,
            @RequestParam(value = "montant", defaultValue = "") Double montant,
            HttpServletRequest request,
            Model model) {
        getUser(model, request);
        String retour = "";
        if (btnDetail.equals("ok")) {
            recupererClientEtCompte(model, cpteE, cpteR);
            retour = "virement";
        }

        if (btnVirement.equals("ok")) {
            int index = codeE.indexOf(",");
            String cpteE2 = codeE.substring(0, index);
            String cpteR2 = codeR.substring(index + 1);
            recupererClientEtCompte2(model, cpteE2, cpteR2, montant);
            retour = "virement-succed";
        }
        return retour;
    }

    public void recupererClientEtCompte(Model model, String cpteE, String cpteR) {

        CompteCourant compteCourant;
        CompteEpargne compteEpargne;

        if (cpteE.contains("CC")) {
            compteCourant = compteCourantRepository.findByCode(cpteE);
            model.addAttribute("compteEmeteur", compteCourant);
            model.addAttribute("clientEmeteur", compteCourant.getClient());
        } else {
            compteEpargne = compteEpargneRepository.findByCode(cpteE);
            model.addAttribute("compteEmeteur", compteEpargne);
            model.addAttribute("clientEmeteur", compteEpargne.getClient());
        }

        if (cpteR.contains("CE")) {
            compteEpargne = compteEpargneRepository.findByCode(cpteR);
            model.addAttribute("compteRecepteur", compteEpargne);
            model.addAttribute("clientRecepteur", compteEpargne.getClient());
        } else {
            compteCourant = compteCourantRepository.findByCode(cpteR);
            model.addAttribute("compteRecepteur", compteCourant);
            model.addAttribute("clientRecepteur", compteCourant.getClient());
        }
    }

    public void recupererClientEtCompte2(Model model, String cpteE, String cpteR, Double val) {

        CompteCourant compteCourant;
        CompteEpargne compteEpargne;

        if (cpteE.contains("CC")) {
            compteCourant = compteCourantRepository.findByCode(cpteE);
            System.out.println("========solde avt mvt :"+compteCourant.getSolde());
            compteCourant.setSolde(compteCourant.getSolde() - val);
            System.out.println("========solde avt mvt :"+compteCourant.getSolde());
            model.addAttribute("compteEmeteur", compteCourant);
            model.addAttribute("clientEmeteur", compteCourant.getClient());
        } else {
            compteEpargne = compteEpargneRepository.findByCode(cpteE);
            compteEpargne.setSolde(compteEpargne.getSolde() - val);
            model.addAttribute("compteEmeteur", compteEpargne);
            model.addAttribute("clientEmeteur", compteEpargne.getClient());
        }

        if (cpteR.contains("CE")) {
            compteEpargne = compteEpargneRepository.findByCode(cpteR);
            compteEpargne.setSolde(compteEpargne.getSolde() + val);
            model.addAttribute("compteRecepteur", compteEpargne);
            model.addAttribute("clientRecepteur", compteEpargne.getClient());
        } else {
            compteCourant = compteCourantRepository.findByCode(cpteR);
            compteCourant.setSolde(compteCourant.getSolde() + val);
            model.addAttribute("compteRecepteur", compteCourant);
            model.addAttribute("clientRecepteur", compteCourant.getClient());
        }
    }
}
