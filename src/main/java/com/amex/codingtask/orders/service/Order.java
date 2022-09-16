package com.amex.codingtask.orders.service;

import com.amex.codingtask.orders.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }


}
