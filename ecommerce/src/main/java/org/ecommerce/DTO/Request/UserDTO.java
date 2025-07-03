package org.ecommerce.DTO.Request;

import org.ecommerce.Entity.Address;
import org.ecommerce.Enum.Role;

import java.util.List;

public record UserDTO (
        String name,
        String email,
        String phone,
        String password,
        List<Address> addresses,
        Boolean isLogin,
        Role role,
        Double wallet

){}
