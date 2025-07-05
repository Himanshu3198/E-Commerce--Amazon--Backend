package org.ecommerce.Controller;

import org.ecommerce.Entity.Order;
import org.ecommerce.Mapper.OrderMapper;
import org.ecommerce.Service.Interface.IOrderService;
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
        Order order = orderService.placeOrder(userId);
        LOGGER.info("Order has been placed with orderDetail: {}",order.toString());
        return ResponseEntity.ok("Order has been placed with orderDetail: "+order.toString());
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId){
        Order order = orderService.cancelOrder(orderId);
        LOGGER.info("Order has been canceled with orderDetail: {}",order.toString());
        return ResponseEntity.ok("Order has bee cancelled with OrderDetail: "+order.toString());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String,Object>> getOrderById(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);
        LOGGER.info("Order has been fetch with orderDetail: {}",order.toString());
        return ResponseEntity.ok(OrderMapper.toDTO(order));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Map<String,Object>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        LOGGER.info("All Order has been fetch with orderDetail: {}", orders.toString());
        return ResponseEntity.ok(orders.stream().map(OrderMapper::toDTO).toList());
    }

}
