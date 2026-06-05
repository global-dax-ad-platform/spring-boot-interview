package com.global.dax.interview.web;

import com.global.dax.interview.model.Order;
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

    private final OrderService orderLineService;
    private final OrderValidator orderLineValidator;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        log.info("GET /order/{} called", id);
        final var orderLine = orderLineService.getOrderById(id);
        return ResponseEntity.ok(orderLine);
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody Order orderLine) {
        log.info("POST /order called");
        orderLineValidator.validateCreate(orderLine);
        final var orderLineId = orderLineService.createOrder(orderLine);
        return ResponseEntity.status(HttpStatus.OK).body(orderLineId);
    }

}
