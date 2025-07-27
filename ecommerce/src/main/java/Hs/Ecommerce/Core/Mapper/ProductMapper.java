package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.DTO.Request.ProductDTO;
import Hs.Ecommerce.Core.DTO.Response.ProductResponseDTO;
import Hs.Ecommerce.Core.Entity.Product;
import Hs.Ecommerce.Core.Enum.AvailabilityStatus;
import Hs.Ecommerce.Core.Enum.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    // Convert from Request DTO to Entity
    public static Product toEntity(ProductDTO productDTO) {
        return new Product()
                .setProductName(productDTO.productName())
                .setPrice(productDTO.price())
                .setDescription(productDTO.description())
                .setCategory(productDTO.category())
                .setAvailabilityStatus(productDTO.availabilityStatus())
                .setImage(productDTO.image())
                .setQuantity(productDTO.quantity());
    }

    // Convert from Entity to Response DTO
    public static ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getImage(),
                product.getAvailabilityStatus().toString(),
                product.getCategory().toString(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    // Convert Page<Product> to Page<ProductResponseDTO>
    public static Page<ProductResponseDTO> toPageDTO(Page<Product> productPage) {
        List<ProductResponseDTO> dtoList = productPage.getContent()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, productPage.getPageable(), productPage.getTotalElements());
    }
}
