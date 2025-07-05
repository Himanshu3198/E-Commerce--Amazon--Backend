package Hs.Ecommerce.Core.DTO.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartDTO(
        @NotNull(message = "useId cannot be null")
        @JsonProperty(value = "userId")
        Long userId,
        @JsonProperty(value = "productId")
        Long productId,
        @Min(value = 1)
        @JsonProperty(value = "quantity")
        Long quantity
) {}
