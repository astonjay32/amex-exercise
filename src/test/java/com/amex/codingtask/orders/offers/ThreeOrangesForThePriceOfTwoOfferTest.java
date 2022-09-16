package com.amex.codingtask.orders.offers;

import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.products.Orange;
import com.amex.codingtask.orders.service.CustomerOrder;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ThreeOrangesForThePriceOfTwoOfferTest {

    @Test
    public void testApply_zeroOranges(){
        CustomerOrder order = new CustomerOrder();
        order.addProduct(new Apple());
        assertThat(new ThreeOrangesForPriceOfTwoOffer().apply(order), is(0F));
    }

    @Test
    public void testApply_threeOranges(){
        CustomerOrder order = new CustomerOrder();
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        assertThat(new ThreeOrangesForPriceOfTwoOffer().apply(order), is(.25F));
    }

    @Test
    public void testApply_sixOranges(){
        CustomerOrder order = new CustomerOrder();
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        assertThat(new ThreeOrangesForPriceOfTwoOffer().apply(order), is(.5F));
    }
}
