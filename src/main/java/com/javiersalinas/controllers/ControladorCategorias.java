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

import com.javiersalinas.models.Category;
import com.javiersalinas.models.Product;
import com.javiersalinas.services.ServicioCategorias;

import jakarta.validation.Valid;

@Controller
public class ControladorCategorias {
	
	@Autowired
	private final ServicioCategorias servicioCategorias;
	
	public ControladorCategorias(ServicioCategorias servicioCategorias) {
		this.servicioCategorias = servicioCategorias;
	}
	
	// Crear categorias
	@GetMapping("/categories/new")
	public String desplegarAgregarCategoria(@ModelAttribute("categoria") Category categoria) {
		return "agregarcategoria.jsp";
	}
	
	@PostMapping("/categories/new")
	public String procesarAgregarCategoria(@Valid @ModelAttribute("categoria") Category categoria,
											BindingResult validaciones,
											Model model) {
		if(validaciones.hasErrors()) {
			return "agregarcategoria.jsp";
		}
		this.servicioCategorias.agregarCategoria(categoria);
		return "redirect:/";
	}
	
	// Seccion agregar categoria al producto
	@GetMapping("/categories/{id}")
	public String desplegarCategoria(@PathVariable("id") Long id, Model model) {
		Category category = servicioCategorias.obtenerCategoriaPorId(id);
		
		List<Product> products = category.getProducts();
		List<Product> unassignedProducts = servicioCategorias.obtenerProductosNoAsignados(id);
		
		// Depuración
		System.out.println("Productos asignados: "+ products);
        System.out.println("Categorías no asignadas: " + unassignedProducts);
		
		model.addAttribute("products", products);
		model.addAttribute("unassignedProducts", unassignedProducts);
		model.addAttribute("category", category);
		model.addAttribute("formProductos", new FormProductos());
		return "categorias.jsp";
	}
	
	@PostMapping("/categories/{id}")
	public String procesarAgregarProductoALaCategoria(@PathVariable("id") Long id, @ModelAttribute("formProductos") FormProductos formProductos) {
		System.out.println("Adding product " + formProductos.getProductId() + " to category " + id);
		if (formProductos.getProductId() != null) {
            servicioCategorias.agregarProductoALaCategoria(id, formProductos.getProductId());
        } else {
            // Manejo error
            System.out.println("El ID del producto es nulo.");
        }
        return "redirect:/categories/" + id;
	}
	
}
