package com.arces.products;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class ProductsService {

    @Autowired
    private ProductsRepository repo;

    public List<Products> listAll() {
        return repo.findAll();
    }

    public void save(Products products) {
        repo.save(products);

    }

    public Products get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }



 

    // public List<Products> getProductsByNameContainsIgnoreCase(@Param("name") String name) {
	// 	return repo.findByNameContainsIgnoreCase(name);
        
	// }

    public List<Products> searchByName(String name){

        return repo.searchByName(name);
    }

    public List<Products> searchByPrice(Float price){

        return repo.searchByPrice(price);
    }
}