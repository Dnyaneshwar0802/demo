package com.tcs.RestController;

import com.tcs.model.Order;
import com.tcs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/orders")
    public class OrderController {

        @Autowired
        private OrderService orderService;

        @GetMapping("/users-who-ordered")
        public ResponseEntity<List<String>> getUsersWhoOrderedProduct(@RequestParam String productName) {
            List<String> userNames = orderService.getUsersWhoOrderedProduct(productName);
            return ResponseEntity.ok(userNames);
        }

}

