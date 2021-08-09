package com.microservice.sagaorchestrator.service;

import com.microservice.sagaorchestrator.domain.Order;
import com.microservice.sagaorchestrator.domain.OrderEvent;
import com.microservice.sagaorchestrator.domain.OrderState;
import com.microservice.sagaorchestrator.repository.OrderRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HotelAction implements Action<OrderState, OrderEvent> {
    private final JmsTemplate jmsTemplate;
    private final OrderRepository orderRepository;

    public HotelAction(JmsTemplate jmsTemplate, OrderRepository orderRepository) {
        super();
        this.jmsTemplate = jmsTemplate;
        this.orderRepository = orderRepository;
    }

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        System.out.println("Hotel Action");
        String orderId = (String) context.getMessage().getHeaders().get("ORDER_ID_HEADER");
        //find order from DB
        Optional<Order> orderOptional = orderRepository.findById(Long.parseLong(orderId));
        jmsTemplate.convertAndSend("hotel-queue", orderOptional.get());
    }
}
