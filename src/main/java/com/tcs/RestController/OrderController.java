package com.tcs.RestController;

import com.tcs.model.Order;
import com.tcs.model.Product;
import com.tcs.model.User;
import com.tcs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/orders")
    public class OrderController {
        @Autowired
        OrderService orderService;
        @PostMapping("/place")
        public ResponseEntity<Order> placeOrder(
                @RequestParam String userId,
                @RequestBody List<String> productIds
        ) {
            return ResponseEntity.ok(orderService.placeOrder(userId, productIds));
        }

        @GetMapping("/users-who-ordered/{productId}")
        public ResponseEntity<List<User>> getUsersWhoOrderedProduct(
                @PathVariable String productId) {
            return ResponseEntity.ok(orderService.getUsersWhoOrderedProduct(productId));
        }

        @GetMapping("/products-ordered-by/{userId}")
        public ResponseEntity<List<Product>> getProductsOrderedByUser(
                @PathVariable String userId) {
            return ResponseEntity.ok(orderService.getProductsOrderedByUser(userId));
        }
}

