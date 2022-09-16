package com.amex.codingtask.orders;

import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.products.Orange;
import com.amex.codingtask.orders.products.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(Apple.class, Orange.class);
        return objectMapper;
    }
}
