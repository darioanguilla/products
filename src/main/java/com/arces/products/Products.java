package com.arces.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {

    private Integer product_id;
    private String name;
    private Integer quantity_in_stock;
    private Float unit_price;

    public Products() {
    }

    public Products(Integer product_id, String name, Integer quantity_in_stock, Float unit_price) {
        this.product_id = product_id;
        this.name = name;
        this.quantity_in_stock = quantity_in_stock;
        this.unit_price = unit_price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(Integer quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public Float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Float unit_price) {
        this.unit_price = unit_price;
    }

}
