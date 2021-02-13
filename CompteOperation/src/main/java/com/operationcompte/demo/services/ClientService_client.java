package com.operationcompte.demo.services;

import com.operationcompte.demo.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CLIENT-SERVICE")
public interface ClientService_client {
    @GetMapping(path = "/clients/{id}")
    public Client findClientById(@PathVariable("id") Long id);
}
