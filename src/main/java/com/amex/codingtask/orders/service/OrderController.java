package com.amex.codingtask.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder order){
        return new ResponseEntity<>(orderService.receiveOrder(order), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CustomerOrder>> allOrders(){
       return new ResponseEntity<>(orderService.allOrders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrder> getOrder(@PathVariable long id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }
}
