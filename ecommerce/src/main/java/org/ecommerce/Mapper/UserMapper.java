package org.ecommerce.Mapper;

import org.ecommerce.DTO.Request.UserDTO;
import org.ecommerce.Entity.User;

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
}
