package com.my.user.domain.pay.api;

import com.my.user.domain.pay.api.dto.ReqChargeDto;
import com.my.user.domain.pay.api.dto.ResPayDto;
import com.my.user.domain.pay.app.PayFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/pays")
@RestController
public class PayController {

	private final PayFacade payFacade;

	@GetMapping("/balance")
	public ResponseEntity<ResPayDto> getBalance(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		return ResponseEntity.ok(payFacade.getBalance(authorization));
	}

	@PutMapping("/balance-recharge")
	public ResponseEntity<Void> balanceRecharge(HttpServletRequest request, @RequestBody ReqChargeDto reqChargeDto) {
		String authorization = request.getHeader("Authorization");
		payFacade.balanceRecharge(authorization, reqChargeDto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<Void> doPayment(HttpServletRequest request, @PathVariable("bookingId") Long bookingId) {
		String authorization = request.getHeader("Authorization");
		payFacade.pay(authorization, bookingId);
		return ResponseEntity.noContent().build();
	}

}
