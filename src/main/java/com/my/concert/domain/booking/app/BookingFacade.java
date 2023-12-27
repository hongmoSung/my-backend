package com.my.concert.domain.booking.app;

import com.my.concert.domain.booking.api.dto.RequestBookingDto;
import com.my.concert.domain.booking.domain.BookingService;
import com.my.concert.domain.seat.domain.Seat;
import com.my.concert.domain.seat.domain.SeatService;
import com.my.user.domain.token.domain.Token;
import com.my.user.domain.token.domain.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingFacade {

    private final TokenService tokenService;
    private final SeatService seatService;
    private final BookingService bookingService;

    public void booking(String authorization, RequestBookingDto dto) {
        if (authorization == null || !authorization.contains("Bearer ")) {
            return;
        }
        Token token = tokenService.decodeToken(authorization.replace("Bearer ", ""));
        String userUuid = token.getUserUuid();
        Long seatId = dto.getSeatId();

        Seat seat = seatService.getSeat(seatId);
        bookingService.booking(userUuid, seat);
    }
}
