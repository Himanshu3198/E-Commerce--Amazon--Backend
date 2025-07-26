package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.DTO.Request.AddressDTO;
import Hs.Ecommerce.Core.DTO.Request.UserDTO;
import Hs.Ecommerce.Core.DTO.Response.AddressResponseDTO;
import Hs.Ecommerce.Core.DTO.Response.UserResponseDTO;
import Hs.Ecommerce.Core.Entity.Address;
import Hs.Ecommerce.Core.Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity(UserDTO userDTO) {
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
                        .setUser(user))
                .collect(Collectors.toList());

        user.setAddresses(addresses);
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        List<AddressResponseDTO> addressDTOs = user.getAddresses().stream()
                .map(addr -> new AddressResponseDTO(
                        addr.getHouseNumber(),
                        addr.getStreetName(),
                        addr.getLocality(),
                        addr.getCity(),
                        addr.getState()
                ))
                .collect(Collectors.toList());

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getNumber(),
                addressDTOs
        );
    }
}