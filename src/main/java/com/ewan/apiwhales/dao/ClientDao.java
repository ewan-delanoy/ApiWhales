package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long> {
    Client findByNom(String nom);

    Client findByUtilisateurId(Long clientId);
}
