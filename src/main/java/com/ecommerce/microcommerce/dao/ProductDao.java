package com.ecommerce.microcommerce.dao;

import java.util.List;

import com.ecommerce.microcommerce.model.Product;

public interface ProductDao {

	/**
	 * 
	 * @return liste des produits
	 */
	List<Product> findAll();
	
	/**
	 * 
	 * @param id
	 * @return Le produit d'id en parametre
	 */
    Product findById(int id);
    
    /**
     * 
     * @param product Le produit sauvegarde
     * @return
     */
    Product save(Product product);
    
}
