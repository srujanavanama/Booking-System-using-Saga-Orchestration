package com.microservice.hotelservice.repository;

import com.microservice.hotelservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Order, Long> {
}
