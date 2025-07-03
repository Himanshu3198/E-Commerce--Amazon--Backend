package org.ecommerce.DTO.Request;

import org.ecommerce.Enum.AvailabilityStatus;
import org.ecommerce.Enum.Category;

public record ProductDTO(
        String productName,
        String description,
        Double price,
        Category category,
        AvailabilityStatus availabilityStatus,
        String image,
        Long quantity
){}
