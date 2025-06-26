package org.ecommerce.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    private User  user;


    private List<CartItem> cartItems;

    @Column(name = "total_amount")
    private Double totalAmount;


}
