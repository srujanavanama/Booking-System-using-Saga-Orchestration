package com.microservice.airlineservice.repository;

import com.microservice.airlineservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Order, Long> {
}
