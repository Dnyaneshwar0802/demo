package com.tcs.repository;



import com.tcs.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    // You can add custom queries if needed
}

