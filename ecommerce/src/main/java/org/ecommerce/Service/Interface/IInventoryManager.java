package org.ecommerce.Service.Interface;

import org.ecommerce.Entity.OrderItem;
import org.ecommerce.Entity.Product;

import java.util.List;

public interface IInventoryManager {

        void addProduct(Product product);
        Product getProductById(Long id);
        List<Product> getAllProducts();
        void updateProductByOrder(List<OrderItem> orderItems);

}
