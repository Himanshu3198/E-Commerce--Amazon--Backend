package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.DTO.Request.AddressDTO;
import Hs.Ecommerce.Core.DTO.Request.UserDTO;
import Hs.Ecommerce.Core.Entity.Address;
import Hs.Ecommerce.Core.Entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntity(UserDTO userDTO){
        if (userDTO == null) {
            throw new RuntimeException("User DTO is null");
        }

        User user = new User()
                .setName(userDTO.name())
                .setEmail(userDTO.email())
                .setWallet(0.0)
                .setLogin(true)
                .setPassword(userDTO.password())
                .setNumber(userDTO.phone())
                .setRole(userDTO.role());

        List<AddressDTO> addressesDto = userDTO.addresses();

        List<Address> addresses = addressesDto.stream()
                .map(record -> new Address()
                        .setCity(record.city())
                        .setHouseNumber(record.houseNumber())
                        .setLocality(record.locality())
                        .setState(record.state())
                        .setStreetName(record.streetName())
                        .setUser(user)) // Set user inside map
                .collect(Collectors.toList());

        user.setAddresses(addresses); // Optional but good to maintain bi-directional consistency
        return user;
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
