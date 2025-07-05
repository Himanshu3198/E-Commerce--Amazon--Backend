package Hs.Ecommerce.Core.DTO.Request;


public record AddressDTO(
        String houseNumber,
        String streetName,
        String locality,
        String state,
        String city
) {}
