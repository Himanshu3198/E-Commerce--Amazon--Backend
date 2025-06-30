package org.ecommerce.Payment;

import org.BookMyShow.Entity.User;
import org.BookMyShow.Enum.PaymentType;

public interface PaymentStrategy {
    public boolean pay(String bookingId, Double amount, User user);
    public PaymentType getType();
}
