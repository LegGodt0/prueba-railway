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
public class ServicioProductos {

	@Autowired
	private final RepositorioProductos repositorioProductos;
	
	@Autowired
    private final RepositorioCategorias repositorioCategorias;
	
	public ServicioProductos(RepositorioProductos repositorioProductos, RepositorioCategorias repositorioCategorias) {
		this.repositorioProductos = repositorioProductos;
		this.repositorioCategorias = repositorioCategorias;
	}
	
	//Obtener productos
	public List<Product> obtenerProductos() {
		return this.repositorioProductos.findAll();
	}
	
	//Crear producto
	public Product agregarProducto(Product producto) {
		return this.repositorioProductos.save(producto);
	}
	
	//Obtener por id
	public Product obtenerProductoPorId(Long id) {
		return this.repositorioProductos.findById(id).orElse(null);
	}
	
	
	
	//Obener categorias por producto
	public List<Category> obtenerCategoriasPorIdProducto(Long productId) {
		Optional<Product> productOpt = repositorioProductos.findById(productId);
		if(productOpt.isPresent()) {
			return productOpt.get().getCategories();
		}
		else {
			return new ArrayList<>();
		}
	}
	
	public List<Category> obtenerCategoriasNoAsignadas(Long productId) {
		Product product = repositorioProductos.findById(productId).orElse(null);
		
		if (product != null) {
			List<Category> todasLasCategorias = (List<Category>) repositorioCategorias.findAll();
			List<Category> categoriasAsignadas = obtenerProductoPorId(productId).getCategories();
			todasLasCategorias.removeAll(categoriasAsignadas);
			return todasLasCategorias;
		}
		return new ArrayList<>();
		
		
	}
	
	public void agregarCategoriaAlProducto(Long productId, Long categoryId) {
		
		Product product = repositorioProductos.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
		Category category = repositorioCategorias.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));

		if (product != null && category != null) {
			product.getCategories().add(category);
			repositorioProductos.save(product);
		}
		
		
	}
}
