package com.aw.train.guiderest.repository;

import com.aw.train.guiderest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
