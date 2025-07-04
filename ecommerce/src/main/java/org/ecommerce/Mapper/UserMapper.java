package org.ecommerce.Mapper;

import org.ecommerce.DTO.Request.UserDTO;
import org.ecommerce.Entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserMapper {
    public static User toEntity(UserDTO userDTO){
        if(userDTO == null){
            throw new RuntimeException("User DTO is null");
        }

        return new User()
                .setName(userDTO.name())
                .setEmail(userDTO.email())
                .setEmail(userDTO.email())
                .setAddresses(userDTO.addresses())
                .setWallet(0.0)
                .setLogin(true)
                .setPassword(userDTO.password())
                .setNumber(userDTO.phone())
                .setRole(userDTO.role());
    }

    public static Map<String,String> toDTO(User user){
        Map<String,String> response = new HashMap<>();
        response.put("userId",user.getId().toString());
        response.put("userName", user.getName());
        response.put("userEmail", user.getEmail());
        response.put("userNumber", user.getNumber());
        response.put("userAddresses",user.getAddresses().toString());
        return  response;
    }
}
