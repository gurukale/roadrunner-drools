package com.cws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cws.model.Order;

/**
 * Specifies methods used to obtain and modify person related information
 * which is stored in the database.
 * @author 
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
