package com.amex.codingtask.orders.service;

import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.products.Orange;
import org.junit.Before;
import org.junit.Test;
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
        assertThat(orderResponse.getSummary(), is("Summary:\nApple - $0.6\nOrange - $0.25\nTotal: $0.85"));

    }
}
