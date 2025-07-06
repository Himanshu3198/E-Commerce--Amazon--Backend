package Hs.Ecommerce.Core.Entity;

import jakarta.persistence.*;
import Hs.Ecommerce.IdentityAccessManagement.RoleType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
       private List<Address> addresses;

       @Column(name = "is_login", nullable = false)
       private Boolean isLogin;

       @Enumerated(EnumType.STRING)
       @Column(name = "role", nullable = false)
       private RoleType role;

       @Column(name = "wallet", nullable = false)
       private Double wallet;

       @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
       private Cart cart;


       @CreationTimestamp
       @Column(name = "created_at")
       private LocalDateTime createdAt;

       @UpdateTimestamp
       @Column(name = "updated_at")
       private LocalDateTime updatedAt;

       // Getters
       public Long getId() {
              return id;
       }

       public String getName() {
              return name;
       }

       public String getEmail() {
              return email;
       }

       public String getNumber() {
              return number;
       }

       public String getPassword() {
              return password;
       }

       public List<Address> getAddresses() {
              return addresses;
       }

       public Boolean getLogin() {
              return isLogin;
       }

       public RoleType getRole() {
              return role;
       }

       public Double getWallet() {
              return wallet;
       }

       public LocalDateTime getCreatedAt() {
              return createdAt;
       }

       public LocalDateTime getUpdatedAt() {
              return updatedAt;
       }

       // Chained Setters
       public User setId(Long id) {
              this.id = id;
              return this;
       }

       public User setName(String name) {
              this.name = name;
              return this;
       }

       public User setEmail(String email) {
              this.email = email;
              return this;
       }

       public User setNumber(String number) {
              this.number = number;
              return this;
       }

       public User setPassword(String password) {
              this.password = password;
              return this;
       }

       public User setAddresses(List<Address> addresses) {
              this.addresses = addresses;
              return this;
       }

       public User setLogin(Boolean login) {
              this.isLogin = login;
              return this;
       }

       public User setRole(RoleType role) {
              this.role = role;
              return this;
       }

       public User setWallet(Double wallet) {
              this.wallet = wallet;
              return this;
       }

       public User setCreatedAt(LocalDateTime createdAt) {
              this.createdAt = createdAt;
              return this;
       }

       public User setUpdatedAt(LocalDateTime updatedAt) {
              this.updatedAt = updatedAt;
              return this;
       }

       @Override
       public String toString() {
              return "User{" +
                      "id=" + id +
                      ", name='" + name + '\'' +
                      ", email='" + email + '\'' +
                      ", number='" + number + '\'' +
                      ", password='" + password + '\'' +
                      ", addresses=" + addresses +
                      ", isLogin=" + isLogin +
                      ", role=" + role +
                      ", wallet=" + wallet +
                      ", createdAt=" + createdAt +
                      ", updatedAt=" + updatedAt +
                      '}';
       }
}
