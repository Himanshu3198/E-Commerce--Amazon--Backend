package org.ecommerce.Controller;


import org.ecommerce.Enum.PaymentMethod;
import org.ecommerce.Service.Interface.ICheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecom/checkout")
public class CheckoutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutController.class);
    @Autowired
    private ICheckoutService checkoutService;

    @PostMapping("/")
    public ResponseEntity<String> checkout(@RequestParam Long orderId, @RequestParam PaymentMethod paymentMethod){
        checkoutService.checkout(orderId,paymentMethod);
        LOGGER.info("Checkout has been completed for the orderId: {}",orderId);
        return ResponseEntity.ok("Checkout has been completed for the orderId: "+orderId);
    }
}
