/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objis.controllers;

import com.objis.entities.Client;
import com.objis.entities.CompteCourant;
import com.objis.entities.CompteEpargne;
import com.objis.entities.Conseiller;
import com.objis.entities.Users;
import com.objis.repositories.ClientRepository;
import com.objis.repositories.CompteCourantRepository;
import com.objis.repositories.CompteEpargneRepository;
import com.objis.repositories.ConseillerRepository;
import com.objis.repositories.UsersRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author yeo-sglo
 */
@Controller
@RequestMapping(value = "/proxibanque")
@SessionAttributes("myUser")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ConseillerRepository conseillerRepository;

    @Autowired
    CompteCourantRepository compteCourantRepository;

    @Autowired
    CompteEpargneRepository compteEpargneRepository;

    @Autowired
    UsersRepository usersRepository;
    

    public void getUser(Model model, HttpSession session, HttpServletRequest request) {
        String user = request.getRemoteUser();
        Users myUser = usersRepository.findByUsername(user); 
        session.setAttribute("myUser", myUser);
    }
    
    public Conseiller getConseillerConnected(HttpSession session){
        Users users = (Users) session.getAttribute("myUser");
        return conseillerRepository.findByUsers(users);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String clients(Model model, HttpSession session, HttpServletRequest request) {
        getUser(model, session, request);
        model.addAttribute("clients", clientRepository.findByConseiller(getConseillerConnected(session)));
        return "clients";
    }

    @RequestMapping(value = "/creer-client", method = RequestMethod.GET)
    public String ajouterClient(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("clientRecapt", null);
        return "nvoClient";
    }

    @RequestMapping(value = "/ajouterClient", method = RequestMethod.POST)
    public String ajoutClient(
            Client client,HttpSession session,
            @RequestParam(value = "cpteE", defaultValue = "non") String cpteE,
            @RequestParam(value = "depotCE", defaultValue = "0") double depotCE,
            @RequestParam(value = "cpteC", defaultValue = "non") String cpteC,
            @RequestParam(value = "depotCC", defaultValue = "0") double depotCC,
            Model model) {
        CompteEpargne compteEpargne = null;
        CompteCourant compteCourant = null;
        System.out.println(cpteC + " " + depotCE + "\n" + cpteE + " " + depotCC);
        Client saveClient = clientRepository.save(client);
        saveClient.setConseiller(getConseillerConnected(session));
        saveClient.setCode_client();
        saveClient = clientRepository.saveAndFlush(saveClient);
        if (cpteE.equals("CE")) {
            compteEpargne = new CompteEpargne();
            compteEpargne.setClient(saveClient);
            compteEpargne.setSolde(depotCE);
            compteEpargne = compteEpargneRepository.saveAndFlush(compteEpargne);
            compteEpargne.setCode();
            compteEpargne = compteEpargneRepository.save(compteEpargne);
        }
        if (cpteC.equals("CC")) {
            compteCourant = new CompteCourant();
            compteCourant.setClient(saveClient);
            compteCourant.setSolde(depotCC);
            compteCourant = compteCourantRepository.saveAndFlush(compteCourant);
            compteCourant.setCode();
            compteCourantRepository.save(compteCourant);
        }

        model.addAttribute("clientRecapt", client);
        if (compteCourant != null) {
            model.addAttribute("compteCourant", compteCourant);
        }
        if (compteEpargne != null) {
            model.addAttribute("compteEpargne", compteEpargne);
        }

        return "nvoClient";
    }
    

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(Long id, Model model) {
        Client client = clientRepository.findOne(id);
        setClientComptes(model, client);
        model.addAttribute("client", client);
        return "details";
    }

    @RequestMapping(value = "/editer")
    public String editer(Long id, Model model) {
        Client client = clientRepository.findOne(id);
        model.addAttribute("client", client);
        System.out.println(client.getId() + " " + client.getPrenom());
        return "editer";
    }

    @RequestMapping(value = "/modifier")
    public String modifier(Client client, Model model, HttpSession session) {
        clientRepository.save(client);
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("clients", clientRepository.findByConseiller(getConseillerConnected(session)));
        return "clients";
    }

    /**
     * permet de rechercher les comptes d'un client puis de les mettrent dans le
     * model
     *
     * @param model
     * @param client
     */
    public void setClientComptes(Model model, Client client) {
        CompteCourant compteCourant = compteCourantRepository.findByClient(client);
        CompteEpargne compteEpargne = compteEpargneRepository.findByClient(client);
        model.addAttribute("compteCourant", compteCourant);
        model.addAttribute("compteEpargne", compteEpargne);
    }

}
