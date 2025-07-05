package Hs.Ecommerce.Core.Controller;

import Hs.Ecommerce.Core.DTO.Request.CartDTO;
import Hs.Ecommerce.Core.Entity.Cart;
import Hs.Ecommerce.Core.Mapper.CartMapper;
import Hs.Ecommerce.Core.Service.Interface.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ecom/api/cart")
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductInCart(@RequestBody CartDTO cartDTO) {
        cartService.addToCart(cartDTO.userId(), cartDTO.productId(), cartDTO.quantity());
        LOGGER.info("Product has been added to cart: {}", cartDTO.productId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to cart.");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeFromCart(userId, productId);
        LOGGER.info("Product removed from cart: {}", productId);
        return ResponseEntity.ok("Product removed from cart: " + productId);
    }

    @PatchMapping("/quantity/increase")
    public ResponseEntity<String> increaseQuantity(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.incrementQuantity(userId, productId);
        LOGGER.info("Quantity increased for product: {}", productId);
        return ResponseEntity.ok("Quantity increased for product: " + productId);
    }

    @PatchMapping("/quantity/decrease")
    public ResponseEntity<String> decreaseQuantity(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.decrementQuantity(userId, productId);
        LOGGER.info("Quantity decreased for product: {}", productId);
        return ResponseEntity.ok("Quantity decreased for product: " + productId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, String>> getUserCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        LOGGER.info("Fetched cart for user: {}", userId);
        return ResponseEntity.ok(CartMapper.toDTO(cart)); // Consider returning a CartDTO or ApiResponse
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        LOGGER.info("Cart cleared for user: {}", userId);
        return ResponseEntity.ok("Cart cleared for user: " + userId);
    }
}
