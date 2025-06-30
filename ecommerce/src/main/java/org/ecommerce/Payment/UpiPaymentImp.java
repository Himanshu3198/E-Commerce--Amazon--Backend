//package org.ecommerce.Payment;
//
//import org.BookMyShow.Entity.User;
//import org.BookMyShow.Enum.PaymentType;
//
//public class UpiPaymentImp implements PaymentStrategy{
//    @Override
//    public boolean pay(String bookingId, Double amount, User user) {
//
//        System.out.println("UPI Payment Successful for booking: "+bookingId+" amount: "+amount+" by user: "+user.getName());
//        return true;
//
//    }
//
//    @Override
//    public PaymentType getType() {
//        return PaymentType.UPI;
//    }
//}
