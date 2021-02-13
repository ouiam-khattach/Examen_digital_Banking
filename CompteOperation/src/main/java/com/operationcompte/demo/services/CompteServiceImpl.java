package com.operationcompte.demo.services;

import com.operationcompte.demo.entities.Compte;
import com.operationcompte.demo.entities.Operation;
import com.operationcompte.demo.repository.CompteRepository;
import com.operationcompte.demo.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CompteServiceImpl implements CompteService{

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    ClientService_client clientService_client;


    @Override
    public Compte AddCompte(Compte compte) {
        return compteRepository.save(compte);
    }


    @Override
    public Operation effectuerVeremant(Long id, double montant) {
        Compte c=compteRepository.getOne(id);
        Operation operation=new Operation();
        operation.setCompte(c);
        operation.setMontant(montant);
        operation.setType("virement");
        c.setSold(c.getSold()+montant);
        compteRepository.save(c);
        operation=operationRepository.save(operation);
        return operation;
    }

    @Override
    public Operation effectuerretirer(Long id, double montant) {
        Compte c=compteRepository.getOne(id);
        Operation operation=new Operation();
        operation.setCompte(c);
        operation.setMontant(montant);
        operation.setType("retirer");
        c.setSold(c.getSold()-montant);
        compteRepository.save(c);
        operation=operationRepository.save(operation);

        return operation;
    }

    @Override
    public void virement(Long id1, Long id2, double montant) {

    }

    @Override
    public Compte getCompte(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void verment(Long id1, Long id2 ,double montant) {
        effectuerretirer(id1,montant);
        effectuerVeremant(id2,montant);
    }

    @Override
    public Compte getCompta(Long id) {
        Compte c=compteRepository.getOne(id);
        c.setClient(clientService_client.findClientById(c.getClient_ID()));
        return c;
    }

    @Override
    public Compte activerCompte(Long id) {
        Compte compte=compteRepository.getOne(id);
        compte.setEtat("Activer");
        compteRepository.save(compte);
        return compte;
    }

    @Override
    public Compte suspendreCompte(Long id) {
        Compte c=compteRepository.getOne(id);
        c.setEtat("suspendre");
        compteRepository.save(c);
        return c;
    }

    @Override
    public List<Operation> getOperation(Long id) {
        Compte c=compteRepository.getOne(id);
        return c.getOperations();
    }
}
