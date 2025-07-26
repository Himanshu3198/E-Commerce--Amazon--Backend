package Hs.Ecommerce.Core.Controller;

import Hs.Ecommerce.Core.DTO.Response.UserResponseDTO;
import jakarta.validation.Valid;
import Hs.Ecommerce.Core.DTO.Request.UserDTO;
import Hs.Ecommerce.Core.Entity.User;
import Hs.Ecommerce.Core.Mapper.UserMapper;
import Hs.Ecommerce.Core.Service.Interface.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId){
         User user = userService.getUserById(userId);
         LOGGER.info("User found by id:{}",user.toString());
         return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(UserMapper::toDTO).toList());
    }

}
