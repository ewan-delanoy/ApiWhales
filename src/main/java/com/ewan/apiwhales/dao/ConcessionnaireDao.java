package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.Concessionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcessionnaireDao extends JpaRepository<Concessionnaire, Long> {

    Concessionnaire findByNom(String nom);
}
