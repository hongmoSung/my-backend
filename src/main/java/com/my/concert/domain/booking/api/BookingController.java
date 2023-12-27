package com.my.concert.domain.booking.api;

import com.my.concert.domain.booking.api.dto.RequestBookingDto;
import com.my.concert.domain.booking.app.BookingFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class BookingController {

    private final BookingFacade bookingFacade;

    @PostMapping("/booking")
    public ResponseEntity<Void> booking(HttpServletRequest request, @RequestBody RequestBookingDto dto) {
        String authorization = request.getHeader("Authorization");
        bookingFacade.booking(authorization, dto);
        return ResponseEntity.created(null).build();
    }
}
