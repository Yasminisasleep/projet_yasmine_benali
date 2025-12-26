package com.epita.projet.projet_yasmine_benali.repository;

import com.epita.projet.projet_yasmine_benali.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    long countByConseillerId(Long conseillerId);
}
