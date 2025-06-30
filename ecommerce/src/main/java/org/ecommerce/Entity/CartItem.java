package org.ecommerce.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name = "quantity")
    @Min(value = 0, message = "Product quantity cannot be negative")
    private Long quantity;

    @ManyToOne(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name ="cart_id",nullable = false)
    private Cart cart;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

//    setter
public CartItem setId(Long id) {
    this.id = id;
    return this;
}

    public CartItem setProduct(Product product) {
        this.product = product;
        return this;
    }

    public CartItem setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public CartItem setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public CartItem setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CartItem setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }





}
