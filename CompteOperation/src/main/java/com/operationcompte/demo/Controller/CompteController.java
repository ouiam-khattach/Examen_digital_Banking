package com.operationcompte.demo.Controller;

import com.operationcompte.demo.entities.Compte;
import com.operationcompte.demo.entities.Operation;
import com.operationcompte.demo.repository.CompteRepository;
import com.operationcompte.demo.services.ClientService_client;
import com.operationcompte.demo.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CompteController {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    CompteService competService;

    @Autowired
    ClientService_client clientServiceclient;

    @GetMapping("Comptes")
    public List<Compte> getCompte() {
        List<Compte> compts = compteRepository.findAll();
        compts.forEach(compte -> {
            compte.setClient(clientServiceclient.findClientById(compte.getClient_ID()));
        });
        return compts;
    }

    @PostMapping("Comptes")
    public Compte addCompte(@RequestBody Compte compte) {
        return competService.AddCompte(compte);
    }

    @PostMapping("compte/virment")
    public Operation virment(@RequestParam("id") Long id, @RequestParam("m") double m) {
        return competService.effectuerViremant(id, m);
    }

    @PostMapping("compte/retrait")
    public Operation retrait(@RequestParam("id") Long id, @RequestParam("m") double m) {
        return competService.effectuerretirer(id, m);
    }

    @GetMapping("compte/{id}")
    public Compte getCompte(@PathVariable("id") Long id) {
        return competService.getCompte(id);
    }

    @PostMapping("compte/activer")
    public Compte activerCompte(@RequestParam("id") Long id) {
        return competService.activerCompte(id);
    }

    @PostMapping("compte/suspendre")
    public Compte suspendreCompte(@RequestParam("id") Long id) {
        return CompteService.suspendreCompte(id);
    }


    @GetMapping("compte/operation/{id}")
    public List<Operation> getOperation(@PathVariable("id") Long id) {
        return CompteService.getOperation(id);
    }

}
