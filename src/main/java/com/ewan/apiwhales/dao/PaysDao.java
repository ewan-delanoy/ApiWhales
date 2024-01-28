package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysDao extends JpaRepository<Pays, Long> {

    Pays findByCode(String code);
}
