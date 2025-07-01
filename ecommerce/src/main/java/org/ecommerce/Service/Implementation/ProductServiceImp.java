package org.ecommerce.Service.Implementation;

import org.ecommerce.Entity.Product;
import org.ecommerce.Exception.ProductNotFoundException;
import org.ecommerce.Repository.ProductRepository;
import org.ecommerce.Service.Interface.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements IProductService {

    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImp.class);

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void createProduct(Product product) {
        try {
            productRepository.save(product);
            LOGGER.info("Product saved successfully: {}", product);
        } catch (DataAccessException dae) {
            LOGGER.error("Error saving product: {}", dae.getMessage(), dae);
            throw new RuntimeException("Database error during product creation", dae);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        try {
            LOGGER.info("Fetching product by ID: {}", id);
            return productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        } catch (DataAccessException dae) {
            LOGGER.error("Error fetching product by ID: {}", dae.getMessage(), dae);
            throw new RuntimeException("Database error during product lookup", dae);
        }
    }



    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        try {
            LOGGER.info("Fetching all products");
            List<Product> products = productRepository.findAll();
            if (products.isEmpty()) {
                throw new ProductNotFoundException("No products found");
            }
            return products;
        } catch (DataAccessException dae) {
            LOGGER.error("Error fetching all products: {}", dae.getMessage(), dae);
            throw new RuntimeException("Database error during product fetch", dae);
        }
    }
}
