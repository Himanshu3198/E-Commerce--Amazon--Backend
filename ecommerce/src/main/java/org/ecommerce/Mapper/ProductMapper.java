package org.ecommerce.Mapper;

import org.ecommerce.DTO.Request.ProductDTO;
import org.ecommerce.Entity.Product;
import org.ecommerce.Enum.AvailabilityStatus;
import org.ecommerce.Enum.Category;

import java.util.HashMap;
import java.util.Map;

public class ProductMapper {

    public static Product toEntity(ProductDTO productDTO){
        return new Product()
                .setProductName("iPhone 15")
                .setPrice(99999.99)
                .setDescription("Latest Apple phone")
                .setCategory(Category.ELECTRONICS)
                .setAvailabilityStatus(AvailabilityStatus.IN_STOCK)
                .setImage("/img1.png")
                .setQuantity(10L);
    }

    public static Map<String,String> toDTO(Product product){
        Map<String,String> response = new HashMap<>();
        response.put("productId",product.getId().toString());
        response.put("productName", product.getProductName());
        response.put("productDescription", product.getDescription());
        response.put("productImage", product.getImage());
        response.put("availabilityStatus",product.getAvailabilityStatus().toString());
        response.put("productCategory",product.getCategory().toString());
        response.put("productQuantity",product.getQuantity().toString());
        response.put("productPrice",product.getPrice().toString());
        return  response;
    }
}
