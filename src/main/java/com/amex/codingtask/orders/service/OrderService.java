package com.amex.codingtask.orders.service;

import com.amex.codingtask.orders.offers.Offer;
import com.amex.codingtask.orders.products.Product;
import com.amex.codingtask.orders.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private OrderRepo orderRepo;

    private List<Offer> activeOffers = new ArrayList();

    public CustomerOrder receiveOrder(CustomerOrder order){
        order.setSubTotal(calculateSubTotal(order));
        order.setTotal(calculateTotalCost(order));
        order.setSummary(createSummary(order));

        orderRepo.save(order);
        return order;
    }

    public Iterable<CustomerOrder> allOrders(){
        return orderRepo.findAll();
    }

    public CustomerOrder getOrderById(Long id){
        return Optional.of(orderRepo.findById(id)).get().orElse(null);
    }

    @Autowired
    public void setActiveOffers(List<Offer> activeOffers){
        this.activeOffers = activeOffers;
    }

    Float calculateSubTotal(CustomerOrder order){
        Float totalCost = 0f;
        for(Product product : order.getProducts()){
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    Float calculateTotalCost(CustomerOrder order){
        Float subTotal = calculateSubTotal(order);
        Float offerDiscounts = applyOfferDiscounts(order);
        return subTotal - offerDiscounts;
    }

    Float applyOfferDiscounts(CustomerOrder order){
        Float offerDiscount = 0F;
        for(Offer offer : activeOffers){
            offerDiscount += offer.apply(order);
        }
        return offerDiscount;
    }

    String createSummary(CustomerOrder order){
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
