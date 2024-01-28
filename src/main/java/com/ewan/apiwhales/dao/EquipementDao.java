package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementDao extends JpaRepository<Equipement, Long> {

    Equipement findByNbDeLitsAndNbDeFauteuils(byte nbDeLits, byte nbDeFauteuils);

}

