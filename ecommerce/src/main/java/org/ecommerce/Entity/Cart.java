package org.ecommerce.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "total_amount")
    private Double totalAmount = 0.0;

    @Column(name = "discount")
    private Double discount = 0.0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // === Getters ===
    public Long getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // === Setters (Chained) ===
    public Cart setId(Long id) {
        this.id = id;
        return this;
    }

    public Cart setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public Cart setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public Cart setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Cart setDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public Cart setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Cart setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                ", cartItems=" + cartItems +
                ", totalAmount=" + totalAmount +
                ", discount=" + discount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
