package Hs.Ecommerce.Core.Entity;

import jakarta.persistence.*;
import Hs.Ecommerce.IdentityAccessManagement.RoleType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id",nullable = false,unique = true)
       private Long id;

       @Column(name = "name", nullable = false)
       private String name;

       @Column(name = "email", nullable = false)
       private String email;

       @Column(name = "phone", nullable = false)
       private String number;

       @Column(name = "password", nullable = false)
       private String password;

       @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
       @JsonIgnore  // Prevent recursion during serialization
       private List<Address> addresses;

       @Column(name = "is_login", nullable = false)
       private Boolean isLogin;

       @Enumerated(EnumType.STRING)
       @Column(name = "role", nullable = false)
       private RoleType role;

       @Column(name = "wallet", nullable = false)
       private Double wallet;

       @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
       @JsonIgnore  // Prevent recursion during serialization
       private Cart cart;

       @CreationTimestamp
       @Column(name = "created_at")
       private LocalDateTime createdAt;

       @UpdateTimestamp
       @Column(name = "updated_at")
       private LocalDateTime updatedAt;

       // Getters and Setters with chaining
       public Long getId() {
              return id;
       }

       public User setId(Long id) {
              this.id = id;
              return this;
       }

       public String getName() {
              return name;
       }

       public User setName(String name) {
              this.name = name;
              return this;
       }

       public String getEmail() {
              return email;
       }

       public User setEmail(String email) {
              this.email = email;
              return this;
       }

       public String getNumber() {
              return number;
       }

       public User setNumber(String number) {
              this.number = number;
              return this;
       }

       public String getPassword() {
              return password;
       }

       public User setPassword(String password) {
              this.password = password;
              return this;
       }

       public List<Address> getAddresses() {
              return addresses;
       }

       public User setAddresses(List<Address> addresses) {
              this.addresses = addresses;
              return this;
       }

       public Boolean getLogin() {
              return isLogin;
       }

       public User setLogin(Boolean login) {
              isLogin = login;
              return this;
       }

       public RoleType getRole() {
              return role;
       }

       public User setRole(RoleType role) {
              this.role = role;
              return this;
       }

       public Double getWallet() {
              return wallet;
       }

       public User setWallet(Double wallet) {
              this.wallet = wallet;
              return this;
       }

       public LocalDateTime getCreatedAt() {
              return createdAt;
       }

       public User setCreatedAt(LocalDateTime createdAt) {
              this.createdAt = createdAt;
              return this;
       }

       public LocalDateTime getUpdatedAt() {
              return updatedAt;
       }

       public User setUpdatedAt(LocalDateTime updatedAt) {
              this.updatedAt = updatedAt;
              return this;
       }

       public Cart getCart() {
              return cart;
       }

       public User setCart(Cart cart) {
              this.cart = cart;
              return this;
       }

       @Override
       public String toString() {
              return "User{" +
                      "id=" + id +
                      ", name='" + name + '\'' +
                      ", email='" + email + '\'' +
                      ", number='" + number + '\'' +
                      ", isLogin=" + isLogin +
                      ", role=" + role +
                      ", wallet=" + wallet +
                      ", createdAt=" + createdAt +
                      ", updatedAt=" + updatedAt +
                      '}';
       }
}
