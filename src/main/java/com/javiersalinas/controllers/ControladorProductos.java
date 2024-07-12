package com.javiersalinas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javiersalinas.models.Category;
import com.javiersalinas.models.Product;
import com.javiersalinas.services.ServicioCategorias;
import com.javiersalinas.services.ServicioProductos;

import jakarta.validation.Valid;

@Controller
public class ControladorProductos {
	
	@Autowired
	private final ServicioProductos servicioProductos;
	
	@Autowired
	private final ServicioCategorias servicioCategorias;
	
	public ControladorProductos(ServicioProductos servicioProductos, ServicioCategorias servicioCategorias) {
		this.servicioProductos = servicioProductos;
		this.servicioCategorias = servicioCategorias;
	}
	
	@GetMapping("")
	public String desplegarInicio(Model model) {
		List<Category> categorias = servicioCategorias.obtenerCategorias();
		List<Product> productos = servicioProductos.obtenerProductos();
		
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", categorias);
		
		return "index.jsp";
	}
	
	// Seccion agregar producto
	@GetMapping("/products/new")
	public String desplegarAgregarProducto(@ModelAttribute("producto") Product producto) {
		return "agregarproducto.jsp";
	}
	
	@PostMapping("/products/new")
	public String procesarAgregarProducto(@Valid @ModelAttribute("producto") Product producto,
											BindingResult validaciones,
											Model model) {
		if(validaciones.hasErrors()) {
			return "agregarproducto.jsp";
		}
		this.servicioProductos.agregarProducto(producto);
		return "redirect:/";
	}
	
	// Pagina de producto
	@GetMapping("/products/{id}")
	public String desplegarProducto(@PathVariable("id") Long id, Model model) {
		Product product = servicioProductos.obtenerProductoPorId(id);
		
		List<Category> categories = product.getCategories();
        List<Category> unassignedCategories = servicioProductos.obtenerCategoriasNoAsignadas(id);
        
        // Depuración
        System.out.println("Categorías asignadas: "+categories);
        System.out.println("Categorías no asignadas: "+unassignedCategories);
        
        model.addAttribute("categories", categories);
        model.addAttribute("unassignedCategories", unassignedCategories);
        model.addAttribute("product", product);
        model.addAttribute("formCategorias", new FormCategorias()); // Agregar una categoría por defecto
		return "productos.jsp";
	}
	
	@PostMapping("/products/{id}")
	public String procesarAgregarCategoriaAProducto(@PathVariable("id") Long id, @ModelAttribute("formCategorias") FormCategorias formCategorias) {
		if(formCategorias.getCategoryId() != null) {
			servicioProductos.agregarCategoriaAlProducto(id, formCategorias.getCategoryId());
		}
		else { // Manejo del error si el ID de la categoría es nulo
			System.out.println("El ID de la categoría es nulo.");
		}
		
		return "redirect:/products/"+ id;
	}
}
