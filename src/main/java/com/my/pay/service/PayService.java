package com.my.pay.service;

import com.my.pay.controller.PaymentDto;
import com.my.pay.controller.ReqChargeDto;
import com.my.pay.controller.ResWalletDto;
import com.my.pay.repo.ReserveEntity;
import com.my.pay.repo.ReserveRepo;
import com.my.pay.repo.WalletEntity;
import com.my.pay.repo.WalletRepo;
import com.my.user.service.Token;
import com.my.user.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PayService {

    private final UserService userService;
    private final WalletRepo walletRepo;
    private final ReserveRepo reserveRepo;

    public ResWalletDto getBalance(String authorization) {
        if (authorization == null || !authorization.contains("Bearer ")) {
            return null;
        }

        Token token = userService.decodeToken(authorization.replace("Bearer ", ""));
        WalletEntity wallet = walletRepo.findByUserUserId(UUID.fromString(token.getUserUuid()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return ResWalletDto.builder()
            .email(token.getEmail())
            .money(wallet.getMoney())
            .build();
    }

    @Transactional
    public void balanceRecharge(String authorization, ReqChargeDto reqChargeDto) {
        if (authorization == null || !authorization.contains("Bearer ")) {
            return;
        }

        Token token = userService.decodeToken(authorization.replace("Bearer ", ""));
        WalletEntity wallet = walletRepo.findByUserUserId(UUID.fromString(token.getUserUuid()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.rechargeMoney(reqChargeDto.getChargeAmount());
    }

    @Transactional
    public void doPayment(String authorization, PaymentDto paymentDto) {
        if (authorization == null || !authorization.contains("Bearer ")) {
            return;
        }

        Token token = userService.decodeToken(authorization.replace("Bearer ", ""));

        ReserveEntity reserveEntity = reserveRepo.findById(paymentDto.getReserveId())
            .orElseThrow(() -> new RuntimeException("Reserve not found"));

        WalletEntity wallet = walletRepo.findByUserUserId(UUID.fromString(token.getUserUuid()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.payMoney(reserveEntity.getPrice());
    }
}
