package com.amex.codingtask.orders.offers;

import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.products.Orange;
import com.amex.codingtask.orders.service.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ThreeOrangesForThePriceOfTwoOfferTest {

    @Test
    public void testApply_zeroOranges(){
        Order order = new Order();
        order.addProduct(new Apple());
        assertThat(new ThreeOrangesForPriceOfTwoOffer().apply(order), is(0F));
    }

    @Test
    public void testApply_threeOranges(){
        Order order = new Order();
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        assertThat(new ThreeOrangesForPriceOfTwoOffer().apply(order), is(.25F));
    }

    @Test
    public void testApply_sixOranges(){
        Order order = new Order();
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        order.addProduct(new Orange());
        assertThat(new ThreeOrangesForPriceOfTwoOffer().apply(order), is(.5F));
    }
}
