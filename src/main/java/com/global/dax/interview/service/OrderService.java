package com.global.dax.interview.service;

import com.global.dax.interview.model.Order;
import com.global.dax.interview.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderLineRepository;

    public Order getOrderById(Integer id) {
        return orderLineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order line with id " + id + " not found"));
    }

    public Integer createOrder(final Order orderLine) {
        log.info("Creating order line with name '{}'", orderLine.getName());
        return orderLineRepository.save(orderLine).getId();
    }
}
