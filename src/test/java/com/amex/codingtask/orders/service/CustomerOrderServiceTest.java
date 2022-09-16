package com.amex.codingtask.orders.service;

import com.amex.codingtask.orders.offers.BogoApplesOffer;
import com.amex.codingtask.orders.offers.Offer;
import com.amex.codingtask.orders.products.Apple;
import com.amex.codingtask.orders.products.Orange;
import com.amex.codingtask.orders.repo.OrderRepo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class CustomerOrderServiceTest {

    private OrderService orderService;
    private OrderRepo orderRepo = mock(OrderRepo.class);

    @Before
    public void setup(){

        orderService = new OrderService();
        ReflectionTestUtils.setField(orderService, "orderRepo", orderRepo);

    }

    @Test
    public void testReceiveOrder(){

        CustomerOrder order = new CustomerOrder();
        order.addProduct(new Apple());
        order.addProduct(new Orange());

        order = orderService.receiveOrder(order);
        assertThat(order.getSubTotal(), is(.85f));
        assertThat(order.getTotal(), is(.85f));
        assertThat(order.getSummary(), is("Summary:\nApple - $0.6\nOrange - $0.25\nSubtotal: $0.85\nTotal: $0.85"));

        verify(orderRepo, times(1)).save(order);

    }

    @Test
    public void testReceiveOrder_withDiscount(){

        List<Offer> activeOffers = new ArrayList<>();
        activeOffers.add(new BogoApplesOffer());
        orderService.setActiveOffers(activeOffers);

        CustomerOrder order = new CustomerOrder();
        order.addProduct(new Apple());
        order.addProduct(new Apple());

        order = orderService.receiveOrder(order);
        assertThat(order.getSubTotal(), is(1.2f));
        assertThat(order.getTotal(), is(.6f));

        assertThat(order.getSummary(), is("Summary:\nApple - $0.6\nApple - $0.6\nSubtotal: $1.2\nDiscount: Buy one get one free on Apples (-$0.6)\nTotal: $0.6"));

        verify(orderRepo, times(1)).save(order);

    }

    @Test
    public void testGetAllOrders(){
        Iterable<CustomerOrder> mockOrders = mock(Iterable.class);
        when(orderRepo.findAll()).thenReturn(mockOrders);
        assertThat(orderService.allOrders(), is(mockOrders));
    }

    @Test
    public void testGetOrder(){
        CustomerOrder mockOrder = mock(CustomerOrder.class);
        when(orderRepo.findById(1l)).thenReturn(Optional.of(mockOrder));
        assertThat(orderService.getOrderById(1l), is(mockOrder));
    }
}
