package Hs.Ecommerce.Core.Service.Interface;

import Hs.Ecommerce.Core.Entity.OrderItem;
import Hs.Ecommerce.Core.Entity.Product;

import java.util.List;

public interface IInventoryManager {

        void addProduct(Product product);
        Product getProductById(Long id);
        List<Product> getAllProducts();
        void updateProductByOrder(List<OrderItem> orderItems);

}
