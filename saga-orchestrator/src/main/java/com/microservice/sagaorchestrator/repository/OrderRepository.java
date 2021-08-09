package com.microservice.sagaorchestrator.repository;

import com.microservice.sagaorchestrator.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
