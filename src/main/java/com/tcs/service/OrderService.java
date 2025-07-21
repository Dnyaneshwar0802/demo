package com.tcs.service;
import com.tcs.model.Order;
import com.tcs.model.Product;
import com.tcs.model.User;
import com.tcs.repository.OrderRepository;
import com.tcs.repository.ProductRepository;
import com.tcs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    public List<String> getUsersWhoOrderedProduct(String productName) {
        // Find product by name
        Product product = productRepo.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Find orders containing this product
        List<Order> orders = orderRepo.findByProductIdsContaining(product.getId());

        // Extract user IDs from orders
        Set<String> userIds = orders.stream()
                .map(Order::getUserId)
                .collect(Collectors.toSet());

        // Fetch users
        List<User> users = userRepo.findAllById(userIds);

        // Return user names
        return users.stream().map(User::getName).collect(Collectors.toList());
    }
}