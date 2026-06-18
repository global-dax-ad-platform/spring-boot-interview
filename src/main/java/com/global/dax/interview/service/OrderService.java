package com.global.dax.interview.service;

import com.global.dax.interview.model.order.Order;
import com.global.dax.interview.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order with id " + id + " not found"));
    }

    public Integer createOrder(final Order order) {
        log.info("Creating order with name '{}'", order.getName());
        return orderRepository.save(order).getId();
    }
}
