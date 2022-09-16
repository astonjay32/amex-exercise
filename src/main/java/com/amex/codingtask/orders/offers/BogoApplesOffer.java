package com.amex.codingtask.orders.offers;

import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.service.Order;
import org.springframework.stereotype.Component;

@Component
public class BogoApplesOffer implements Offer{

    @Override
    public String name() {
        return "Buy one get one free on Apples";
    }

    @Override
    public Float apply(Order order) {

        long numApples = order.getProducts().stream().filter(p -> p instanceof Apple).count();
        if(numApples > 1){
            int freeApples = (int) Math.floor(numApples/2);
            return freeApples * (new Apple()).getPrice();
        }
        return 0F;
    }
}
