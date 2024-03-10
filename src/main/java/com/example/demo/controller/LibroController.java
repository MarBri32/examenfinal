package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Libro;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/libros")
	public String getAllLibro(Model model) {
		
		List<Libro> lisLibros=libroService.getAllLibros();
		model.addAttribute("libros",lisLibros);
		return "libroList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGeneros());
		return "libroRegister";
	}
	
	@PostMapping("/register")
	public String createLibro(@RequestParam("nombre") String nombre,
								@RequestParam("autor") String autor,
								@RequestParam ("fechapub") String fechapub, 
								@RequestParam("idgenero") Long idgenero, Model model) {
		
			Libro libro = new Libro();
			libro.nombre= nombre;
			libro.autor= autor;
			libro.fechapub= fechapub;
			
			Genero genero = generoService.getGeneroById(idgenero);
			
			libro.genero= genero;
			
			libroService.createLibro(libro);
			
			model.addAttribute("libros", libroService.getAllLibros());
			model.addAttribute("generos", generoService.getAllGeneros());
			
			return "libroList";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {		

		Libro libro = libroService.getLibroByID(id);
		
		model.addAttribute("libro", libro);
		model.addAttribute("generos", generoService.getAllGeneros());

		return "libroEdit";
	}
	
	@PostMapping("/edit")
	public String createLibro(@RequestParam("id") Long id, @RequestParam("nombre") String nombre,
								@RequestParam("autor") String autor,
								@RequestParam("fechapub") String fechapub,
								@RequestParam("idgenero") Long idgenero, Model model) {
		
			Libro libro = libroService.getLibroByID(id);
			libro.nombre= nombre;
			libro.autor= autor;
			libro.fechapub= fechapub;
			
			Genero genero = generoService.getGeneroById(idgenero);
			
			libro.genero= genero;
			
			libroService.createLibro(libro);
			
			model.addAttribute("libros", libroService.getAllLibros());
			model.addAttribute("generos", generoService.getAllGeneros());
			
			return "libroList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteLibro(@PathVariable Long id, Model model) {
		libroService.deleteLibro(id);
		
		model.addAttribute("libros", libroService.getAllLibros());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "libroList";
	}
}
