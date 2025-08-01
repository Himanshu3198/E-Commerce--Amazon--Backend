package Hs.Ecommerce.Core.Controller;


import Hs.Ecommerce.Core.DTO.Response.ProductResponseDTO;
import jakarta.validation.Valid;
import Hs.Ecommerce.Core.DTO.Request.ProductDTO;
import Hs.Ecommerce.Core.Entity.Product;
import Hs.Ecommerce.Core.Mapper.ProductMapper;
import Hs.Ecommerce.Core.Service.Interface.IInventoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ecom/products")
public class ProductController {

    @Autowired
    private IInventoryManager inventoryManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDTO productDTO){
        inventoryManager.addProduct(ProductMapper.toEntity(productDTO));
        LOGGER.info("Product has been added to inventory: {}",productDTO.productName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product has been added to inventory");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId){
        Product product = inventoryManager.getProductById(productId);
        LOGGER.info("Product has been fetch by id: {}",product.toString());
        return ResponseEntity.ok(ProductMapper.toDTO(product));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<Product> products = inventoryManager.getAllProducts();
        LOGGER.info("All product has been fetched!");
        return ResponseEntity.ok(products.stream().map(ProductMapper::toDTO).toList());
    }


}
