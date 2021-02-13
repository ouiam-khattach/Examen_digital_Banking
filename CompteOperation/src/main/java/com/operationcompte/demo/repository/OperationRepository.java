package com.operationcompte.demo.repository;

import com.operationcompte.demo.entities.Compte;
import com.operationcompte.demo.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
