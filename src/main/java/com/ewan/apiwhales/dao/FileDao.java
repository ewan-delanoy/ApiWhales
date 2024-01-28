package com.ewan.apiwhales.dao;

import com.ewan.apiwhales.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, Long> {

}
