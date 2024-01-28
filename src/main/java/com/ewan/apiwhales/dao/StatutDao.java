package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutDao extends JpaRepository<Statut, Long> {

    public Statut findByNom(String nom);
}

