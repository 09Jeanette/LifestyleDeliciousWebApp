package com.javaguides.lifestyledelicioswebApp.repository;

import com.javaguides.lifestyledelicioswebApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByProductId(Integer productId);
}