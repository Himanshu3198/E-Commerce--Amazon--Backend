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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/ecom/api/users/")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final IUserService userService;


    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO){
        userService.addUser(UserMapper.toEntity(userDTO));
        LOGGER.info("User has been added successfully :{}",userDTO.name());
        return ResponseEntity.status(HttpStatus.CREATED).body("User has been created successfully");
    }



}
