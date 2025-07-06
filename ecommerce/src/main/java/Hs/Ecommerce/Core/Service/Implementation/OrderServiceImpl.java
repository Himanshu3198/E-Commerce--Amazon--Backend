package Hs.Ecommerce.Core.Service.Implementation;

import Hs.Ecommerce.Core.Entity.*;

import Hs.Ecommerce.Core.Enum.OrderStatus;
import Hs.Ecommerce.Core.Exception.OrderNotFoundException;
import Hs.Ecommerce.Core.Exception.ResourceNotFoundException;
import Hs.Ecommerce.Core.Repository.OrderRepository;
import Hs.Ecommerce.Core.Service.Interface.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final UserServiceImpl userServiceImp;
    private final CartServiceImp cartServiceImp;

    public OrderServiceImpl(OrderRepository orderRepository, UserServiceImpl userServiceImp, CartServiceImp cartServiceImp) {
        this.orderRepository = orderRepository;
        this.userServiceImp = userServiceImp;
        this.cartServiceImp = cartServiceImp;
    }

    @Override
    @Transactional
    public synchronized Order placeOrder(Long userId) {
        try {
            final Cart cart = cartServiceImp.getCartByUserId(userId);
            final User user = userServiceImp.getUserById(userId);
            if (cart == null) {
                throw new ResourceNotFoundException("UserCart not found for userId: " + userId);
            }
            Address address = cart.getuser().getAddresses().getFirst();
            if(address == null){
                throw new ResourceNotFoundException("Customer address is null: "+userId);
            }


            final Order order = new Order()
                    .setId(Long.valueOf(UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 8)))
                    .setOrderStatus(OrderStatus.PENDING)
                    .setCustomer(user)
                    .setAddress(address)
                    .setTotalAmount(cart.getTotalAmount())
                    .setCreatedAt(LocalDateTime.now())
                    .setUpdatedAt(LocalDateTime.now());

            final List<OrderItem> orderItems = cart.getCartItems().stream()
                    .map(item -> new OrderItem()
                            .setProduct(item.getProduct())
                            .setOrderQuantity(item.getQuantity())
                            .setOrder(order)
                            .setCreatedAt(LocalDateTime.now())
                            .setUpdatedAt(LocalDateTime.now()))
                    .collect(Collectors.toList());

            order.setOrderItems(orderItems);

            return orderRepository.save(order);
        } catch (DataAccessException dae) {
            logAndThrow("Database error occurred while placing order", dae);
        } catch (IndexOutOfBoundsException ioobe) {
            LOGGER.error("Index error while processing cart items: {}", ioobe.getMessage(), ioobe);
            throw new RuntimeException("Error while accessing cart or order item list.");
        } catch (Exception ex) {
            logAndThrow("Unexpected error occurred while placing order", ex);
        }
        return null; // Should not reach here
    }

    @Override
    @Transactional
    public Order cancelOrder(Long orderId) {
        try {
            final Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Cannot find order with id: " + orderId));

            final User customer = order.getCustomer();
            customer.setWallet(customer.getWallet() + order.getTotalAmount());

            order.setOrderStatus(OrderStatus.CANCELLED)
                    .setUpdatedAt(LocalDateTime.now());

            userServiceImp.addUser(customer);
            return orderRepository.save(order);
        } catch (DataAccessException dae) {
            logAndThrow("Database error occurred while cancelling order", dae);
        } catch (Exception ex) {
            logAndThrow("Unexpected error occurred while cancelling order", ex);
        }
        return null;
    }

    @Override
    @Transactional
    public void updateOrderStatus(long orderId, OrderStatus orderStatus) {
        try {
            final Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Cannot find order with id: " + orderId));

            order.setOrderStatus(orderStatus)
                    .setUpdatedAt(LocalDateTime.now());

            orderRepository.save(order); // <-- Save updated order
        } catch (DataAccessException dae) {
            logAndThrow("Database error occurred while updating order status", dae);
        } catch (Exception ex) {
            logAndThrow("Unexpected error occurred while updating order status", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long orderId) {
        try {
            return orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Cannot find order with id: " + orderId));
        } catch (DataAccessException dae) {
            logAndThrow("Database error occurred while retrieving order", dae);
        } catch (Exception ex) {
            logAndThrow("Unexpected error occurred while retrieving order", ex);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrder() {
        try {
            return orderRepository.findAll();
        } catch (DataAccessException dae) {
            logAndThrow("Database error occurred while retrieving all orders", dae);
        } catch (Exception ex) {
            logAndThrow("Unexpected error occurred while retrieving all orders", ex);
        }
        return List.of();
    }

    @Override
    @Transactional
    public synchronized void updateOrder(Order order) {
        try {
            orderRepository.save(order);
        } catch (DataAccessException dae) {
            logAndThrow("Database error occurred while retrieving all orders", dae);
        } catch (Exception ex) {
            logAndThrow("Unexpected error occurred while retrieving all orders", ex);
        }
    }

    private void logAndThrow(String message, Exception ex) {
        LOGGER.error("{}: {}", message, ex.getMessage(), ex);
        throw new RuntimeException(message);
    }
}
