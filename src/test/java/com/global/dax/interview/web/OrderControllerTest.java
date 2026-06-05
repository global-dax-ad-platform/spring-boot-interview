package com.global.dax.interview.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.dax.interview.model.DeliveryType;
import com.global.dax.interview.model.Order;
import com.global.dax.interview.model.OrderStatus;
import com.global.dax.interview.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    private MockMvc mockMvc;
    @Autowired private OrderRepository orderLineRepository;
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        orderLineRepository.truncate();
        orderLineRepository.save(Order.builder()
                                         .name("Campaign Q1")
                                         .status(OrderStatus.DRAFT)
                                         .deliveryType(DeliveryType.DIRECT)
                                         .businessId(1)
                                         .build());
    }

    @Test
    void getOrderById_returnsOrder() throws Exception {
        mockMvc.perform(get("/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Campaign Q1"))
                .andExpect(jsonPath("$.status").value("DRAFT"));
    }

    @Test
    void getOrderById_returnsNotFound_whenOrderDoesNotExist() throws Exception {
        mockMvc.perform(get("/order/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Order line with id 99 not found"));
    }

    @Test
    void createOrder_returnsCreatedId() throws Exception {
        final var orderLine = Order.builder()
                .name("New Campaign")
                .status(OrderStatus.DRAFT)
                .deliveryType(DeliveryType.DIRECT)
                .businessId(1)
                .build();

        mockMvc.perform(post("/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderLine)))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void createOrder_returnsBadRequest_whenValidationFails() throws Exception {
        final var orderLine = Order.builder()
                .name("New Campaign")
                .status(OrderStatus.BOOKED)
                .deliveryType(DeliveryType.DIRECT)
                .businessId(1)
                .build();

        mockMvc.perform(post("/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderLine)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Order line creation validation failed"))
                .andExpect(jsonPath("$.errors[0].field").value("orderLine"))
                .andExpect(jsonPath("$.errors[0].message").value("Order line must be in DRAFT status"));
    }
}
