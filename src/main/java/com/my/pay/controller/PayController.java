package com.my.pay.controller;

import com.my.pay.service.PayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/pays")
@RestController
public class PayController {

    private final PayService payService;

    @GetMapping("/balance")
    public ResponseEntity<ResWalletDto> getBalance(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return ResponseEntity.ok(payService.getBalance(authorization));
    }

    @PutMapping("/balance-recharge")
    public ResponseEntity<Void> balanceRecharge(HttpServletRequest request, @RequestBody ReqChargeDto reqChargeDto) {
        String authorization = request.getHeader("Authorization");
        payService.balanceRecharge(authorization, reqChargeDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/payment")
    public ResponseEntity<Void> doPayment(HttpServletRequest request, @RequestBody PaymentDto paymentDto) {
        String authorization = request.getHeader("Authorization");
        payService.doPayment(authorization, paymentDto);
        return ResponseEntity.noContent().build();
    }
}
