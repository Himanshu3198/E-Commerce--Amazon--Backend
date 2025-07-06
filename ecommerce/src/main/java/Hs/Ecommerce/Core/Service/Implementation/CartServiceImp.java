package Hs.Ecommerce.Core.Service.Implementation;

import Hs.Ecommerce.Core.Entity.Cart;
import Hs.Ecommerce.Core.Entity.CartItem;
import Hs.Ecommerce.Core.Entity.Product;
import Hs.Ecommerce.Core.Exception.ResourceNotFoundException;
import Hs.Ecommerce.Core.Repository.CartRepository;
import Hs.Ecommerce.Core.Service.Interface.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartServiceImp implements ICartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImp.class);
    private final CartRepository cartRepository;
    private final InventoryManagerImpl productServiceImp;

    public CartServiceImp(CartRepository cartRepository, InventoryManagerImpl productServiceImp) {
        this.cartRepository = cartRepository;
        this.productServiceImp = productServiceImp;
    }

    @Override
    public Cart addToCart(Long userId, Long productId, Long quantity) {
        try {
            Cart cart = getCartByUserId(userId);
            if (cart == null) {
                throw new ResourceNotFoundException("User cart not found for user ID: " + userId);
            }

            Product product = productServiceImp.getProductById(productId);
            if (product == null) {
                throw new ResourceNotFoundException("Product not found with ID: " + productId);
            }

            CartItem existingItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst().orElse(null);

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
            } else {
                CartItem cartItem = new CartItem()
                        .setProduct(product)
                        .setQuantity(quantity)
                        .setCart(cart)
                        .setCreatedAt(LocalDateTime.now())
                        .setUpdatedAt(LocalDateTime.now());
                cart.getCartItems().add(cartItem);
            }

            calculateTotal(cart);
            cart.setUpdatedAt(LocalDateTime.now());
            return cartRepository.save(cart);
        } catch (DataAccessException dae) {
            LOGGER.error("Database error while adding to cart: {}", dae.getMessage(), dae);
            throw new RuntimeException("Database error occurred while updating the cart.");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while adding to cart: {}", ex.getMessage(), ex);
            throw new RuntimeException("Unexpected error occurred while adding to cart.");
        }
    }

    @Override
    public Cart removeFromCart(Long userId, Long productId) {
        try {
            Cart cart = getCartByUserId(userId);
            if (cart == null) {
                throw new ResourceNotFoundException("User cart not found for user ID: " + userId);
            }

            CartItem cartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst().orElse(null);

            if (cartItem != null) {
                cart.getCartItems().remove(cartItem);
                cartItem.setCart(null);
                calculateTotal(cart);
                cart.setUpdatedAt(LocalDateTime.now());
                return cartRepository.save(cart);
            } else {
                throw new ResourceNotFoundException("Cart item not found for product ID: " + productId);
            }
        } catch (DataAccessException dae) {
            LOGGER.error("Database error while removing from cart: {}", dae.getMessage(), dae);
            throw new RuntimeException("Database error occurred while removing item from cart.");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error while removing from cart: {}", ex.getMessage(), ex);
            throw new RuntimeException("Unexpected error occurred while removing item from cart.");
        }
    }

    @Override
    public Cart incrementQuantity(Long userId, Long productId) {
        try {
            Cart cart = getCartByUserId(userId);
            CartItem cartItem = getCartItem(cart, productId);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            calculateTotal(cart);
            cart.setUpdatedAt(LocalDateTime.now());
            return cartRepository.save(cart);
        } catch (Exception ex) {
            LOGGER.error("Error while incrementing quantity: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while incrementing quantity in cart.");
        }
    }

    @Override
    public Cart decrementQuantity(Long userId, Long productId) {
        try {
            Cart cart = getCartByUserId(userId);
            CartItem cartItem = getCartItem(cart, productId);
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            }
            calculateTotal(cart);
            cart.setUpdatedAt(LocalDateTime.now());
            return cartRepository.save(cart);
        } catch (Exception ex) {
            LOGGER.error("Error while decrementing quantity: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while decrementing quantity in cart.");
        }
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        try {
            LOGGER.info("Fetching cart for user ID: {}", userId);
            return cartRepository.findCartByUser_Id(userId);
        } catch (DataAccessException dae) {
            throw new RuntimeException("Database error while fetching cart: " + dae.getMessage());
        }
    }

    @Override
    public void clearCart(Long userId) {
        try {
            Cart cart = getCartByUserId(userId);
            cart.getCartItems().clear();
            calculateTotal(cart);
            cartRepository.save(cart);
        } catch (DataAccessException dae) {
            throw new RuntimeException("Database error while clearing cart: " + dae.getMessage());
        }
    }

    // === Private Helpers ===

    private void calculateTotal(Cart cart) {
        double total = 0.0;
        if (cart.getCartItems() != null) {
            for (CartItem item : cart.getCartItems()) {
                if (item.getProduct() != null && item.getQuantity() != null) {
                    total += item.getQuantity() * item.getProduct().getPrice();
                }
            }
        }
        cart.setTotalAmount(total - (cart.getDiscount() != null ? cart.getDiscount() : 0.0));
    }

    private CartItem getCartItem(Cart cart, Long productId) {
        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found");
        }
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found for product ID: " + productId));
    }
}
