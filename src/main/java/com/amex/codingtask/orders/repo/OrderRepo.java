package com.amex.codingtask.orders.repo;

import com.amex.codingtask.orders.service.CustomerOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<CustomerOrder, Long> {
}
