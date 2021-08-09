package com.microservice.sagaorchestrator.service;

import com.microservice.sagaorchestrator.domain.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SagaJmsListener {

    private final JmsTemplate jmsTemplate;
    private final OrderService orderService;

    public SagaJmsListener(JmsTemplate jmsTemplate, OrderService orderService) {
        super();
        this.jmsTemplate = jmsTemplate;
        this.orderService = orderService;
    }

    @JmsListener(destination = "saga-queue")
    public void listen(Order order) {
        System.out.println("Message Consumed: " + order);
        orderService.processSagaResponse(order);
    }
}
