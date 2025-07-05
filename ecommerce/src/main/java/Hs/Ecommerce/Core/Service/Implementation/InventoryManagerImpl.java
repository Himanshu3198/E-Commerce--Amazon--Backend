package Hs.Ecommerce.Core.Service.Implementation;

import Hs.Ecommerce.Core.Entity.OrderItem;
import Hs.Ecommerce.Core.Entity.Product;
import Hs.Ecommerce.Core.Exception.InventoryUpdateException;
import Hs.Ecommerce.Core.Exception.ProductNotFoundException;
import Hs.Ecommerce.Core.Repository.ProductRepository;
import Hs.Ecommerce.Core.Service.Interface.IInventoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryManagerImpl implements IInventoryManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryManagerImpl.class);
    private final ProductRepository productRepository;

    public InventoryManagerImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        try {
            productRepository.save(product);
            LOGGER.info("Product saved successfully: {}", product);
        } catch (DataAccessException dae) {
            LOGGER.error("Error saving product: {}", dae.getMessage(), dae);
            throw new InventoryUpdateException("Database error during product creation", dae);
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
            throw new InventoryUpdateException("Database error during product lookup", dae);
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
            throw new InventoryUpdateException("Database error during product fetch", dae);
        }
    }

    @Override
    @Transactional
    public void updateProductByOrder(List<OrderItem> orderItems) {
        try {
            for (OrderItem item : orderItems) {
                Long productId = item.getProduct().getId();
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

                Long remaining =  (product.getQuantity() - item.getOrderQuantity());
                if (remaining < 0) {
                    throw new IllegalArgumentException("Insufficient stock for productId: " + productId);
                }

                product.setQuantity( remaining);
                productRepository.save(product);

                LOGGER.info("Updated productId: {}. Remaining stock: {}", productId, remaining);
            }
        } catch (DataAccessException dae) {
            LOGGER.error("Database error while updating products: {}", dae.getMessage(), dae);
            throw new InventoryUpdateException("Database error during product update", dae);
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while updating inventory: {}", ex.getMessage(), ex);
            throw new InventoryUpdateException("Unexpected error while updating inventory", ex);
        }
    }
}
