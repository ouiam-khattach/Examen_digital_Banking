package com.operationcompte.demo.services;

import com.operationcompte.demo.entities.Compte;
import com.operationcompte.demo.entities.Operation;

import java.util.List;

public interface CompteService {


    public Compte AddCompte(Compte compte);
    public Operation effectuerVeremant(Long id, double montant);
    public Operation effectuerretirer(Long id,double montant);
    public void virement(Long id1,Long id2,double montant);
    public Compte getCompte(Long id);
    public Compte suspendreCompte(Long id);
    public Compte activerCompte(Long id);

    public List<Operation> getOperation(Long id);

}
