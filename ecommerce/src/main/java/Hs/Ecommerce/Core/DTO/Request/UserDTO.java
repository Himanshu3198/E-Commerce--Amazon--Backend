package Hs.Ecommerce.Core.DTO.Request;

import Hs.Ecommerce.Core.Entity.Address;
import Hs.Ecommerce.Core.Enum.Role;

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
