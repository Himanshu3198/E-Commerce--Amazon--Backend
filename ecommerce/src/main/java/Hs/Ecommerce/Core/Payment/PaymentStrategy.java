package Hs.Ecommerce.Core.Payment;

import Hs.Ecommerce.Core.Entity.User;
import Hs.Ecommerce.Core.Enum.PaymentMethod;

public interface PaymentStrategy {
    public boolean pay(String orderId, Double amount, User user);
    public PaymentMethod getType();
}
