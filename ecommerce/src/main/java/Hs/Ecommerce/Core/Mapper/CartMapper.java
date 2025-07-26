package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.DTO.Response.CartItemResponseDTO;
import Hs.Ecommerce.Core.DTO.Response.CartResponseDTO;
import Hs.Ecommerce.Core.Entity.Cart;
import Hs.Ecommerce.Core.Entity.CartItem;
import Hs.Ecommerce.Core.Exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartResponseDTO toDTO(Cart cart) {
        if (cart == null) throw new ResourceNotFoundException("Cart is null or invalid");

        List<CartItemResponseDTO> cartItems = cart.getCartItems().stream()
                .map(CartMapper::mapCartItem)
                .collect(Collectors.toList());

        return new CartResponseDTO(
                cart.getUser().getName(),
                cartItems,
                cart.getDiscount(),
                cart.getTotalAmount(),
                cart.getCreatedAt(),
                cart.getUpdatedAt()
        );
    }

    private static CartItemResponseDTO mapCartItem(CartItem item) {
        return new CartItemResponseDTO(
                item.getProduct().getProductName(),
                item.getProduct().getPrice(),
                item.getQuantity()
        );
    }
}