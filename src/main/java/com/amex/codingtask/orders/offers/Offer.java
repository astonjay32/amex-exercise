package com.amex.codingtask.orders.offers;

import com.amex.codingtask.orders.service.CustomerOrder;

public interface Offer {

    public String name();
    public Float apply(CustomerOrder order);
}
