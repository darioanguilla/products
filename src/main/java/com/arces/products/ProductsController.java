package com.arces.products;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    
    @Autowired
    private ProductsService service;

    @GetMapping("/products")
    public List<Products> list() {
        return service.listAll();
    }

    @GetMapping("/products/{product_id}")
    public ResponseEntity<Products> get(@PathVariable Integer product_id) {

        try {
            Products product = service.get(product_id);
            return new ResponseEntity<Products>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
        }

    }

    // @PostMapping("/products")
    // public void add(@RequestBody Products product) {
    //     service.save(product);
    // }

    @PostMapping("/products")
    public void addAll(@RequestBody Products[] products) {
        
        for (Products p : products){
            service.save(p);
        }
    

    }

    @PutMapping("/products/{product_id}")
    public ResponseEntity<Products> update(@RequestBody Products product, @PathVariable Integer product_id) {
        try {
            Products product_old = service.get(product_id);
            copyNonNullProperties(product, product_old);
            service.save(product_old);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{product_id}")
    public ResponseEntity<?> delete(@PathVariable Integer product_id) {
        try {

            service.delete(product_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

