package com.my.user.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.user.api.dto.ReqTokenDto;
import com.my.user.api.dto.ResTokenDto;
import com.my.user.app.UserFacade;
import com.my.user.domain.UserService;
import com.my.user.domain.pay.api.dto.PaymentDto;
import com.my.user.domain.pay.api.dto.ReqChargeDto;
import com.my.user.domain.pay.api.dto.ResPayDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/tokens")
    public ResponseEntity<ResTokenDto> getToken(@RequestBody ReqTokenDto reqTokenDto) throws JsonProcessingException {
        String token = userFacade.getToken(reqTokenDto.getEmail());
        ResTokenDto resTokenDto = new ResTokenDto(token);
        return ResponseEntity.ok(resTokenDto);
    }

    @GetMapping("/balance")
    public ResponseEntity<ResPayDto> getBalance(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return ResponseEntity.ok(userFacade.getBalance(authorization));
    }

    @PutMapping("/balance-recharge")
    public ResponseEntity<Void> balanceRecharge(HttpServletRequest request, @RequestBody ReqChargeDto reqChargeDto) {
        String authorization = request.getHeader("Authorization");
        userFacade.balanceRecharge(authorization, reqChargeDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/payment")
    public ResponseEntity<Void> doPayment(HttpServletRequest request, @RequestBody PaymentDto paymentDto) {
        String authorization = request.getHeader("Authorization");
        userFacade.pay(authorization);
        return ResponseEntity.noContent().build();
    }
}
