package com.amex.codingtask.orders.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "name")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Apple.class, name = "Apple"),
        @JsonSubTypes.Type(value = Orange.class, name = "Orange") }
)
@JsonIgnoreProperties("name")
public abstract class Product {

    protected String name;
    protected Float price;

    public Product(String name, Float price){
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
