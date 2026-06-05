package com.global.dax.interview.repository;

import com.global.dax.interview.model.Order;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {

    private final Map<Integer, Order> storage = new ConcurrentHashMap<>();
    private Integer nextId = 1;

    public Order save(Order orderLine) {
        if (orderLine.getId() == null) {
            orderLine.setId(nextId++);
        }
        storage.put(orderLine.getId(), orderLine);
        return orderLine;
    }

    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void truncate() {
        storage.clear();
        nextId = 1;
    }

}
