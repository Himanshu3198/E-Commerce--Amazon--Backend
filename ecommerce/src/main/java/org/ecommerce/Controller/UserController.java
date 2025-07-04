package org.ecommerce.Controller;

import jakarta.validation.Valid;
import org.ecommerce.DTO.Request.UserDTO;
import org.ecommerce.Entity.User;
import org.ecommerce.Mapper.UserMapper;
import org.ecommerce.Service.Interface.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/ecom/api/users/")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;


    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO){
        userService.addUser(UserMapper.toEntity(userDTO));
        LOGGER.info("User has been added successfully :{}",userDTO.name());
        return ResponseEntity.status(HttpStatus.CREATED).body("User has been created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,String>> getUserById(@PathVariable Long userId){
         User user = userService.getUserById(userId);
         LOGGER.info("User found by id:{}",user.toString());
         return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Map<String,String>>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(UserMapper::toDTO).toList());
    }

}
