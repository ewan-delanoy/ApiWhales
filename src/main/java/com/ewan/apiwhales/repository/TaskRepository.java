package com.ewan.apiwhales.repository;

import com.ewan.apiwhales.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

}