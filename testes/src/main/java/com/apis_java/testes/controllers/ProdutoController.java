package com.apis_java.testes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis_java.testes.entities.ProdutoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	//TODO: add uma fabrica dps
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(null);
	EntityManager em = emf.createEntityManager();
	
	@GetMapping
	public ResponseEntity<ProdutoEntity> get_produtos() {
		//TODO: add uma query aqui dps
		ProdutoEntity p = new ProdutoEntity("xuxu", 3, 3.0);
		return ResponseEntity.ok(p);
	}
	
}
