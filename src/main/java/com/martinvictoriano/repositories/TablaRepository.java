package com.martinvictoriano.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.martinvictoriano.models.Tabla;

public interface TablaRepository extends CrudRepository<Tabla, Long>{
	List<Tabla> findAll();

}
