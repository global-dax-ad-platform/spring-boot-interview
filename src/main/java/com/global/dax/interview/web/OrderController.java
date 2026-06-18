package com.global.dax.interview.web;

import com.global.dax.interview.model.order.Order;
import com.global.dax.interview.service.OrderService;
import com.global.dax.interview.validation.OrderValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderValidator orderValidator;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        log.info("GET /order/{} called", id);
        final var order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody Order order) {
        log.info("POST /order called");
        orderValidator.validateCreate(order);
        final var orderId = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(orderId);
    }

}
