package com.javiersalinas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javiersalinas.models.Product;

@Repository
public interface RepositorioProductos extends CrudRepository<Product, Long>{
	List<Product> findAll();
}
