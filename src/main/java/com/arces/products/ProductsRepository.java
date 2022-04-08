package com.arces.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {


    // List<Products> findByNameContainsIgnoreCase(String name);
    
    @Query(
        value = "SELECT * FROM sql_store.products WHERE name LIKE %:name% ", 
        nativeQuery = true
    )
    List<Products> searchByName(@Param("name") String name);

    @Query(
        value = "SELECT * FROM sql_store.products WHERE unit_price <= :price ", 
        nativeQuery = true
    )
    List<Products> searchByPrice(@Param("price") Float price);

}