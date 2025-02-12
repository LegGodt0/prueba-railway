package com.javiersalinas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javiersalinas.models.Category;

@Repository
public interface RepositorioCategorias extends CrudRepository<Category, Long>{
	List<Category> findAll();
}