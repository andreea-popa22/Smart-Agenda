package com.example.smartagenda.repository;

import com.example.smartagenda.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select c from Client c where c.clientId = :clientId")
    Optional<Client> findClientById(int clientId);

    @Query("select c from Client c where c.firstName = :firstName and c.lastName = :lastName")
    Optional<Client> findClientByFullName(String firstName, String lastName);

    @Query("select count(c.clientId) from Client c")
    int totalClients();
}
