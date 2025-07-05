package Hs.Ecommerce.Core.DTO.Request;

import Hs.Ecommerce.Core.Enum.AvailabilityStatus;
import Hs.Ecommerce.Core.Enum.Category;

public record ProductDTO(
        String productName,
        String description,
        Double price,
        Category category,
        AvailabilityStatus availabilityStatus,
        String image,
        Long quantity
){}
