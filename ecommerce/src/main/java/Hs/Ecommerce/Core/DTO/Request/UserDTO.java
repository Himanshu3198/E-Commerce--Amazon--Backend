package Hs.Ecommerce.Core.DTO.Request;

import Hs.Ecommerce.IdentityAccessManagement.RoleType;

import java.util.List;

public record UserDTO (
        String name,
        String email,
        String phone,
        String password,
        List<AddressDTO> addresses,
        Boolean isLogin,
        RoleType role,
        Double wallet

){}
