package Hs.Ecommerce.Core.Controller;

import Hs.Ecommerce.Core.DTO.Response.OrderResponseDTO;
import Hs.Ecommerce.Core.Entity.Order;
import Hs.Ecommerce.Core.Mapper.OrderMapper;
import Hs.Ecommerce.Core.Service.Interface.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ecom/order")
public class OrderController {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private IOrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> placeOrder(@PathVariable Long userId){
        OrderResponseDTO order = orderService.placeOrder(userId);
        LOGGER.info("Order has been placed with orderDetail: {}",order.toString());
        return ResponseEntity.ok("Order has been placed with orderDetail: "+order.toString());
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId){
        OrderResponseDTO order = orderService.cancelOrder(orderId);
        LOGGER.info("Order has been canceled with orderDetail: {}",order.toString());
        return ResponseEntity.ok("Order has bee cancelled with OrderDetail: "+order.toString());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);
        LOGGER.info("Order has been fetch with orderDetail: {}",order.toString());
        return ResponseEntity.ok(OrderMapper.toDTO(order));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        LOGGER.info("All Order has been fetch with orderDetail: {}", orders.toString());
        return ResponseEntity.ok(orders.stream().map(OrderMapper::toDTO).toList());
    }

}
