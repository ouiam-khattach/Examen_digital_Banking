package com.operationcompte.demo.repository;

import com.operationcompte.demo.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Long> {
}
