package com.javaguides.lifestyledelicioswebApp.controller;

import com.javaguides.lifestyledelicioswebApp.model.CartItem;
import com.javaguides.lifestyledelicioswebApp.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) {
        try {
            // Save the cart item to the database
            CartItem savedItem = cartItemRepository.save(cartItem);
            return ResponseEntity.ok(savedItem);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Internal server error
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Integer productId) {
        try {
            cartItemRepository.deleteById(productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Item not found
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<CartItem>> getItemsByProductId(@PathVariable Integer productId) {
        List<CartItem> items = cartItemRepository.findByProductId(productId);
        return ResponseEntity.ok(items);
}
}
