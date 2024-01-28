package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.LienDeParente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LienDeParenteDao extends JpaRepository<LienDeParente, Long> {
    LienDeParente findByNom(String lienDeParenteNom);

}
