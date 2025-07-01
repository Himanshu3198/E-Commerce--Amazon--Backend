package org.ecommerce.Service.Interface;

import org.ecommerce.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

        void createProduct(Product product);
        Product getProductById(Long id);
        List<Product> getAllProducts();

}
