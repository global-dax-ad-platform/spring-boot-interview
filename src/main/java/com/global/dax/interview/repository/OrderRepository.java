package com.global.dax.interview.repository;

import com.global.dax.interview.model.order.Order;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {

    private final Map<Integer, Order> storage = new ConcurrentHashMap<>();
    private Integer nextId = 1;

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(nextId++);
        }
        storage.put(order.getId(), order);
        return order;
    }

    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void truncate() {
        storage.clear();
        nextId = 1;
    }

}
