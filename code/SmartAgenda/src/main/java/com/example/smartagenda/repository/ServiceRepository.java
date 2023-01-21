package com.example.smartagenda.repository;

import com.example.smartagenda.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("select s from Service s where s.name like %:name%")
    List<Service> findServicesByName(String name);

    @Query("select s from Service s where s.serviceId = :id")
    Optional<Service> findServiceById(int id);
}
