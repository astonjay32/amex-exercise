package com.amex.codingtask.orders.service;

import com.amex.codingtask.orders.offers.BogoApplesOffer;
import com.amex.codingtask.orders.offers.Offer;
import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.products.Orange;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderServiceTest {

    private OrderService orderService;

    @Before
    public void setup(){

        orderService = new OrderService();

    }

    @Test
    public void testReceiveOrder(){

        Order order = new Order();
        order.addProduct(new Apple());
        order.addProduct(new Orange());

        OrderResponse orderResponse = orderService.receiveOrder(order);
        assertThat(orderResponse.getTotalCost(), is(.85f));
        assertThat(orderResponse.getOrder(), is(order));
        assertThat(orderResponse.getSummary(), is("Summary:\nApple - $0.6\nOrange - $0.25\nSubtotal: $0.85\nTotal: $0.85"));

    }

    @Test
    public void testReceiveOrder_withDiscount(){

        List<Offer> activeOffers = new ArrayList<>();
        activeOffers.add(new BogoApplesOffer());
        orderService.setActiveOffers(activeOffers);

        Order order = new Order();
        order.addProduct(new Apple());
        order.addProduct(new Apple());

        OrderResponse orderResponse = orderService.receiveOrder(order);
        assertThat(orderResponse.getTotalCost(), is(.6f));
        assertThat(orderResponse.getOrder(), is(order));
        assertThat(orderResponse.getSummary(), is("Summary:\nApple - $0.6\nApple - $0.6\nSubtotal: $1.2\nDiscount: Buy one get one free on Apples (-$0.6)\nTotal: $0.6"));

    }
}
