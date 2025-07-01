package org.ecommerce.Payment;

import org.ecommerce.Entity.User;
import org.ecommerce.Enum.PaymentMethod;

public class CardPaymentImp implements PaymentStrategy{
    @Override
    public boolean pay(String orderId, Double amount, User user) {
        System.out.println("Card Payment Successful for booking: "+orderId+" amount: "+amount+" by user: "+user.getName());
        return true;
    }

    @Override
    public PaymentMethod getType() {
        return PaymentMethod.CARD;
    }
}