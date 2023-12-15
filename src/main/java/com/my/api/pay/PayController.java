package com.my.api.pay;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pays")
@RestController
public class PayController {

    @GetMapping("/balance")
    public void getBalance() {
    }

    @PostMapping("/charge")
    public void doCharge() {
    }

    @PostMapping("/payment")
    public void doPayment() {
    }
}
