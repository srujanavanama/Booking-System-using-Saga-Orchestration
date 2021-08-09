package com.microservice.sagaorchestrator.resource;

import com.microservice.sagaorchestrator.domain.Order;
import com.microservice.sagaorchestrator.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
public class OrderResource {
    private final OrderService orderService;


    public OrderResource(OrderService orderService) {
        super();
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity placeOrder(@RequestBody Order order) throws URISyntaxException {
        System.out.println("****************");
        System.out.println("Order received : " + order);
        Order savedOrder = orderService.placeOrder(order);
        return ResponseEntity.created(new URI(savedOrder.getOrderId().toString())).build();
    }
}
