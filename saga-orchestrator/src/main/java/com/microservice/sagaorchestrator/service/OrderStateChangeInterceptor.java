package com.microservice.sagaorchestrator.service;

import com.microservice.sagaorchestrator.domain.Order;
import com.microservice.sagaorchestrator.domain.OrderEvent;
import com.microservice.sagaorchestrator.domain.OrderState;
import com.microservice.sagaorchestrator.repository.OrderRepository;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class OrderStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderState, OrderEvent> {
    private final OrderRepository orderRepository;

    public OrderStateChangeInterceptor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Detect state change (PRE) and persist state of an Order into DB
     * @param state
     * @param message
     * @param transition
     * @param stateMachine
     */
    @Override
    public void preStateChange(State<OrderState, OrderEvent> state, Message<OrderEvent> message,
                               Transition<OrderState, OrderEvent> transition, StateMachine<OrderState, OrderEvent> stateMachine) {
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault("ORDER_ID_HEADER", "")))
                .ifPresent(orderId -> {
                    System.out.println("$$$ Interceptor: orderId " + orderId + " $$$$");
                    System.out.println("Saving state for irder id: " + orderId + " Status: " + state.getId());
//                    Order order = orderRepository.getOne(Long.parseLong(orderId));
                    Optional<Order> order = orderRepository.findById(Long.parseLong(orderId));
                    order.ifPresent(o -> {
                        o.setOrderState(state.getId());
                        orderRepository.saveAndFlush(o);
                    });
//                    if(order.isPresent()) {
//                        order.stream().
//                        order.setOrderState(state.getId());
//                        orderRepository.saveAndFlush(order); // Hibernate is lazyLoading, so to avoid that
//                    }
                });
    }
}
