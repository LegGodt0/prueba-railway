package com.javiersalinas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javiersalinas.models.Category;
import com.javiersalinas.models.Product;
import com.javiersalinas.repositories.RepositorioCategorias;
import com.javiersalinas.repositories.RepositorioProductos;

@Service
public class ServicioCategorias {
	
	@Autowired
	private final RepositorioCategorias repositorioCategorias;
	
	@Autowired
	private final RepositorioProductos repositorioProductos;
	
	public ServicioCategorias(RepositorioCategorias repositorioCategorias, RepositorioProductos repositorioProductos) {
		this.repositorioCategorias = repositorioCategorias;
		this.repositorioProductos = repositorioProductos;
	}
	
	//Obtener categorias
	public List<Category> obtenerCategorias() {
		return this.repositorioCategorias.findAll();
	}
	
	//Crear producto
	public Category agregarCategoria(Category categoria) {
		return this.repositorioCategorias.save(categoria);
	}
	
	//Obtener por id
	public Category obtenerCategoriaPorId(Long id) {
		return this.repositorioCategorias.findById(id).orElse(null);
	}
	
	//Obtener productos por categoria
	public List<Product> obtenerProductosPorIdCategoria(Long categoryId) {
		Optional<Category> categoryOpt = repositorioCategorias.findById(categoryId);
		if(categoryOpt.isPresent()) {
			return categoryOpt.get().getProducts();
		}
		else {
			return new ArrayList<>();
		}
	}
	
	// Obtener productos no asignados a una categoría
	public List<Product> obtenerProductosNoAsignados(Long categoryId) {
		Category category = repositorioCategorias.findById(categoryId).orElse(null);
		
		if (category != null) {
			List<Product> todosLosProductos = (List<Product>) repositorioProductos.findAll();
			List<Product> productosAsignados = category.getProducts();
			todosLosProductos.removeAll(productosAsignados);
			return todosLosProductos;
		}
		return new ArrayList<>();
	}
	
	// Agregar producto a una categoría
	public void agregarProductoALaCategoria(Long categoryId, Long productId) {
		Category category = repositorioCategorias.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
		Product product = repositorioProductos.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
		
		if(category != null && product != null) {
			category.getProducts().add(product);
			repositorioCategorias.save(category);
		}
	}
}
