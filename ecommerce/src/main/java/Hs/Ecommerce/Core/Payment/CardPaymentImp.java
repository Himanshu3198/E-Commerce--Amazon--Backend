package Hs.Ecommerce.Core.Payment;

import Hs.Ecommerce.Core.Entity.User;
import Hs.Ecommerce.Core.Enum.PaymentMethod;

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