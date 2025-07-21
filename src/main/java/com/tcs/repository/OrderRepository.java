package com.tcs.repository;



import com.tcs.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    // You can add custom queries if needed
    List<Order> findByProductIdsContaining(String productId);

}

