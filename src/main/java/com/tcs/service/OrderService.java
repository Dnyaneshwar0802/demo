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
    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;

    public Order placeOrder(String userId, List<String> productIds) {
        Order order = new Order();
        order.setUserId(userId);
        order.setProductIds(productIds);
        return orderRepository.save(order);
    }

    public List<User> getUsersWhoOrderedProduct(String productId) {
        List<Order> orders = orderRepository.findByProductIdsContaining(productId);
        Set<String> userIds = orders.stream()
                .map(Order::getUserId)
                .collect(Collectors.toSet());
        return userRepository.findAllById(userIds);
    }

    public List<Product> getProductsOrderedByUser(String userId) {
        List<Order> orders = orderRepository.findAll()
                .stream().filter(o -> o.getUserId().equals(userId)).toList();
        Set<String> productIds = orders.stream()
                .flatMap(o -> o.getProductIds().stream())
                .collect(Collectors.toSet());
        return productRepository.findAllById(productIds);
    }
}