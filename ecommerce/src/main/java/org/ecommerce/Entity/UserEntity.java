package org.ecommerce.Entity;

import jakarta.persistence.*;
import org.ecommerce.Enum.Role;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "user")
public class UserEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(name = "name",nullable = false)
       private String name;

       @Column(name = "Email",nullable = false)
       private String email;

       @Column(name = "phone",nullable = false)
       private String number;

       @Column(name = "password",nullable = false)
       private String password;

       @Column(name = "address",nullable = false)
       private String address;

       @Column(name = "is_login",nullable = false)
       private Boolean isLogin;

       @Column(name = "role",nullable = false)
       @Enumerated(EnumType.STRING)
       private Role role;

       public Long getId() {
              return id;
       }

       public String getName() {
              return name;
       }

       public void setName(String name) {
              this.name = name;
       }

       public String getEmail() {
              return email;
       }

       public void setEmail(String email) {
              this.email = email;
       }

       public String getNumber() {
              return number;
       }

       public void setNumber(String number) {
              this.number = number;
       }

       public String getPassword() {
              return password;
       }

       public void setPassword(String password) {
              this.password = password;
       }

       public String getAddress() {
              return address;
       }

       public void setAddress(String address) {
              this.address = address;
       }

       public Boolean getLogin() {
              return isLogin;
       }

       public void setLogin(Boolean login) {
              isLogin = login;
       }

       public Role getRole() {
              return role;
       }

       public void setRole(Role role) {
              this.role = role;
       }
}
