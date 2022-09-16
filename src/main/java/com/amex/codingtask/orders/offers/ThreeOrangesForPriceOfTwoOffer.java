package com.amex.codingtask.orders.offers;

import com.amex.codingtask.orders.products.Orange;
import com.amex.codingtask.orders.service.CustomerOrder;
import org.springframework.stereotype.Component;

@Component
public class ThreeOrangesForPriceOfTwoOffer implements Offer{

    @Override
    public String name() {
        return "Three Oranges for the price of Two";
    }

    @Override
    public Float apply(CustomerOrder customerOrder) {

        long numOranges = customerOrder.getProducts().stream().filter(p -> p instanceof Orange).count();
        if(numOranges > 2){
            float orangePrice = new Orange().getPrice();
            return (int) Math.floor(numOranges / 3) * orangePrice;
        }
        return 0F;
    }
}
