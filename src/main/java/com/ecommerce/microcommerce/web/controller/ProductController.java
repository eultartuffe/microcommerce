package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;

@RestController
public class ProductController {

	// Spring se charge d'en fabriquer une instance
	@Autowired
	private ProductDao productDao;

	// Récupérer la liste des produits
	@RequestMapping(value = "/Produits", method = RequestMethod.GET)
	public List<Product> listeProduits() {
		return productDao.findAll();
	}

	// Récupérer un produit par son Id
	@GetMapping(value = "/Produits/{id}")
	public Product afficherUnProduit(@PathVariable int id) {
		return productDao.findById(id);
	}

	// ajouter un produit
	@PostMapping(value = "/Produits")
	// ResponseEntity est une classe qui hérite de HttpEntity,  
	// qui permet de définir le code HTTP  à retourner
	public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) {

		Product productAdded = productDao.save(product);

		if (productAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productAdded.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
