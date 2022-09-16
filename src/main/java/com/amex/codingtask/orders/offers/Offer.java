package com.amex.codingtask.orders.offers;

import com.amex.codingtask.orders.service.Order;

public interface Offer {

    public String name();
    public Float apply(Order order);
}
