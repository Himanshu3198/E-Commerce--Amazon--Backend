package org.ecommerce.Service.Interface;

import org.ecommerce.Entity.Cart;

public interface ICartService {

    Cart addToCart(Long userId, Long productId, Long quantity);

    Cart removeFromCart(Long userId, Long productId);

    Cart incrementQuantity(Long userId, Long productId);

    Cart decrementQuantity(Long userId, Long productId);

    Cart getCartByUserId(Long userId);

    void clearCart(Long userId); // Optional but useful
}

