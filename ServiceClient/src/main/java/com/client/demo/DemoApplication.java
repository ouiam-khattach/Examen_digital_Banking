package com.client.demo;

import com.client.demo.entites.Client;
import com.client.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class DemoApplication {
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository){
        repositoryRestConfiguration.exposeIdsFor(Client.class);
        return args -> {
            Client client1=new Client(null,"4523","user1","user1@gmail.com");
            Client client2=new Client(null,"7854","user2","user2@gmail.com");
            clientRepository.save(client1);
            clientRepository.save(client2);
        };
    }
}
