package com.example.smartagenda.repository;

import com.example.smartagenda.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("select s from Service s where s.name = :name")
    Optional<Service> findByName(String name);
}
