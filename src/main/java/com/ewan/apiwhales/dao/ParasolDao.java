package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.Parasol;
import com.ewan.apiwhales.entity.Plage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ParasolDao extends JpaRepository<Parasol, Long> {

	   List<Parasol> findByFilePlagePlageId(Long plageId);

       @Query("""
   			SELECT p.parasolId 
			FROM Parasol p   
			INNER JOIN p.reservations r 
			WHERE 
			(p.file.plage = :plage) AND
			((r.dateDebut BETWEEN :dateDebut AND :dateFin)  
			OR
			(r.dateFin BETWEEN :dateDebut AND :dateFin))
			""")
       List<Long> idsDesParasolsOccupes(@Param("plage") Plage plage,
                                         @Param("dateDebut") LocalDate dateDebut,
                                         @Param("dateFin") LocalDate dateFin);



        Parasol findByFilePlageAndFileNumeroAndNumEmplacement(
                Plage plage, byte numeroFile, byte numEmplacement
        );

	Parasol findByParasolId(Long parasolId);
}
