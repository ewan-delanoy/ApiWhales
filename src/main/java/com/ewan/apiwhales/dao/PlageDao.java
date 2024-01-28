package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.Plage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlageDao extends JpaRepository<Plage, Long> {

    Plage findByPlageId(Long plageId);
}

