package Hs.Ecommerce.Core.DTO.Response;

import java.util.List;

public record UserResponseDTO(
        Long userId,
        String userName,
        String userEmail,
        String userNumber,
        List<AddressResponseDTO> userAddresses
) {}