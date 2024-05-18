package com.javaguides.lifestyledelicioswebApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_item") // Adjust the table name as needed
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;


    // Default constructor for JPA
    public CartItem() {
    }

    // Constructor with parameters
    public CartItem(Integer productId, String productName, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    // Getters
    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    // Setters
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // toString method
    @Override
    public String toString() {
        return "CartItem{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }


    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;

        CartItem cartItem = (CartItem) o;

        if (!getProductId().equals(cartItem.getProductId())) return false;
        if (!getProductName().equals(cartItem.getProductName())) return false;
        return getPrice().equals(cartItem.getPrice());
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = getProductId().hashCode();
        result = 31 * result + getProductName().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
}
}
