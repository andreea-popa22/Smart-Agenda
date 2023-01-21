package com.example.smartagenda.repository;

import com.example.smartagenda.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    @Query("select p from Provider p where p.providerId = :providerId")
    Optional<Provider> findProviderById(int providerId);

    @Query("select p from Provider p where p.firstName = :firstName and p.lastName = :lastName")
    Optional<Provider> findProviderByFullName(String firstName, String lastName);

    @Query("select count(p.providerId) from Provider p")
    int totalProviders();
}
