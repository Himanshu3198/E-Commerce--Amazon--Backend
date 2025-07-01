package org.ecommerce.Payment;
import org.ecommerce.Entity.User;
import org.ecommerce.Enum.PaymentMethod;


public class UpiPaymentImp implements PaymentStrategy{
    @Override
    public boolean pay(String orderId, Double amount, User user) {

        System.out.println("UPI Payment Successful for order: "+orderId+" amount: "+amount+" by user: "+user.getName());
        return true;

    }

    @Override
    public PaymentMethod getType() {
        return PaymentMethod.UPI;
    }
}
