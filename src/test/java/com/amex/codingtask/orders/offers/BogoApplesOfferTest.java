package com.amex.codingtask.orders.offers;

import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.products.Orange;
import com.amex.codingtask.orders.service.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BogoApplesOfferTest {

    @Test
    public void testApply_zeroApples(){
        Order order = new Order();
        order.addProduct(new Orange());
        assertThat(new BogoApplesOffer().apply(order), is(0F));
    }

    @Test void testApply_1Apple(){
        Order order = new Order();
        order.addProduct(new Apple());
        assertThat(new BogoApplesOffer().apply(order), is(0F));
    }

    @Test void testApple_5Apples(){
        Order order = new Order();
        order.addProduct(new Apple());
        order.addProduct(new Apple());
        order.addProduct(new Apple());
        order.addProduct(new Apple());
        order.addProduct(new Apple());
        assertThat(new BogoApplesOffer().apply(order), is(1.2F));
    }
}
