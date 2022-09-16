package com.amex.codingtask.orders.service;

import com.amex.codingtask.orders.offers.Offer;
import com.amex.codingtask.orders.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private List<Offer> activeOffers = new ArrayList();

    public OrderResponse receiveOrder(Order order){

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrder(order);
        orderResponse.setSubTotal(calculateSubTotal(order));
        orderResponse.setTotalCost(calculateTotalCost(order));
        orderResponse.setSummary(createSummary(order));

        return orderResponse;
    }

    @Autowired
    public void setActiveOffers(List<Offer> activeOffers){
        this.activeOffers = activeOffers;
    }

    Float calculateSubTotal(Order order){
        Float totalCost = 0f;
        for(Product product : order.getProducts()){
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    Float calculateTotalCost(Order order){
        Float subTotal = calculateSubTotal(order);
        Float offerDiscounts = applyOfferDiscounts(order);
        return subTotal - offerDiscounts;
    }

    Float applyOfferDiscounts(Order order){
        Float offerDiscount = 0F;
        for(Offer offer : activeOffers){
            offerDiscount += offer.apply(order);
        }
        return offerDiscount;
    }

    String createSummary(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append("Summary:\n");
        for(Product product : order.getProducts()){
            sb.append(product.getName() + " - " + "$" + product.getPrice() + "\n");
        }
        sb.append("Subtotal: $" + calculateSubTotal(order) + "\n");

        if(applyOfferDiscounts(order) > 0.0F){
            for(Offer offer : activeOffers){
                float offerDiscount = offer.apply(order);
                if(offerDiscount > 0.0F){
                    sb.append("Discount: " + offer.name() + " (-$" + offerDiscount + ")\n");
                }
            }
        }
        sb.append("Total: $" + calculateTotalCost(order));
        return sb.toString();
    }


}
