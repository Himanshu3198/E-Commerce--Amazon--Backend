package Hs.Ecommerce.Core.DTO.Response;

public record AddressResponseDTO(
        String houseNumber,
        String streetName,
        String locality,
        String city,
        String state
) {}