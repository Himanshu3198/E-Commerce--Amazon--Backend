package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.Entity.Cart;
import Hs.Ecommerce.Core.Exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CartMapper {

     public static Map<String,String> toDTO(Cart cart){
         if(cart == null) throw  new ResourceNotFoundException("cart is null or invalid");
         Map<String,String> response = new HashMap<>();
         response.put("customer",cart.getuser().getName());
         response.put("cartItem",cart.getCartItems().toString());
         response.put("discount",cart.getDiscount().toString());
         response.put("totalAmount",cart.getTotalAmount().toString());
         response.put("createdAt",cart.getCreatedAt().toString());
         response.put("updatedAt",cart.getUpdatedAt().toString());
         return  response;
     }
}
