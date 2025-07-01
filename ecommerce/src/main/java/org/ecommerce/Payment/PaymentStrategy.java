package org.ecommerce.Payment;

import org.ecommerce.Entity.User;
import org.ecommerce.Enum.PaymentMethod;

public interface PaymentStrategy {
    public boolean pay(String orderId, Double amount, User user);
    public PaymentMethod getType();
}
