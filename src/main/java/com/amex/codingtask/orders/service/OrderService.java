package com.amex.codingtask.orders.service;

import com.amex.codingtask.orders.products.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public OrderResponse receiveOrder(Order order){

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrder(order);
        orderResponse.setTotalCost(calculateTotalCost(order));
        orderResponse.setSummary(createSummary(order));

        return orderResponse;
    }

    Float calculateTotalCost(Order order){
        Float totalCost = 0f;
        for(Product product : order.getProducts()){
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    String createSummary(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append("Summary:\n");
        for(Product product : order.getProducts()){
            sb.append(product.getName() + " - " + "$" + product.getPrice() + "\n");
        }
        sb.append("Total: $" + calculateTotalCost(order));
        return sb.toString();
    }


}
