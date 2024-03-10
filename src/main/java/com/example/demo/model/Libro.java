package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Size;

@Entity
@Table(name="libro")
public class Libro {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;

	    @Column(name = "nombre", nullable = false, length = 60)
	    @Size(min = 4, max = 60, message = "El nombre del libro tiene entre 4 y 60 caracteres.")
	    public String nombre;

	    @Column(name = "autor", nullable = false, length = 60)
	    @Size(min = 4, max = 60, message = "El nombre del autor tiene entre 4 y 60 caracteres.")
	    public String autor;
	    
	    @Column(name = "fechapub", nullable = false)
	    public String fechapub;	    

	    @ManyToOne
		@JoinColumn(name="idgenero", nullable=false)
		public Genero genero;
	
}
