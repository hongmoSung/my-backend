package com.my.user.domain.pay.api;

import com.my.user.domain.pay.api.dto.PaymentDto;
import com.my.user.domain.pay.api.dto.ReqChargeDto;
import com.my.user.domain.pay.api.dto.ResPayDto;
import com.my.user.domain.pay.domain.PayService;
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

    @PutMapping("/payment")
    public ResponseEntity<Void> doPayment(HttpServletRequest request, @RequestBody PaymentDto paymentDto) {
        String authorization = request.getHeader("Authorization");
        payService.pay(authorization);
        return ResponseEntity.noContent().build();
    }
}
