package com.my.concert.service;

import com.my.concert.controller.dto.ResConcertDto;
import com.my.concert.controller.dto.ResRemainSeats;
import com.my.concert.controller.dto.ReserveDto;
import com.my.concert.repo.ConcertEntity;
import com.my.concert.repo.ConcertRepo;
import com.my.concert.repo.SeatEntity;
import com.my.concert.repo.SeatRepo;
import com.my.concert.repo.ViewingDateEntity;
import com.my.concert.repo.ViewingDateRepo;
import com.my.pay.repo.ReserveEntity;
import com.my.pay.repo.ReserveRepo;
import com.my.user.repo.UserEntity;
import com.my.user.repo.UserRepo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ConcertService {

    private final ConcertRepo concertRepo;
    private final ViewingDateRepo viewingDateRepo;
    private final UserRepo userRepo;
    private final SeatRepo seatRepo;
    private final ReserveRepo reserveRepo;

    @Transactional
    public void createConcert(Concert concert) {
        ConcertEntity concertEntity = concertRepo.save(concert.toEntity());

        for (var i = 0; i <= concert.getConcertPeriodDays(); i++) {
            ViewingDate viewingDate = new ViewingDate(concert.getStartDate().plusDays(i));
            List<Seat> seats = new ArrayList<>();

            for (var j = 0; j < viewingDate.getCapacity(); j++) {
                Seat seat = new Seat(j + 1);
                seats.add(seat);
            }

            ViewingDateEntity viewingDateEntity = viewingDate.toEntity(concertEntity);
            List<SeatEntity> seatEntities = seats
                    .stream().map(seat -> seat.toEntity(viewingDateEntity))
                    .collect(Collectors.toList());
            viewingDateEntity.addSeats(seatEntities);
            viewingDateRepo.save(viewingDateEntity);
        }
    }

    public ResConcertDto getAvailableDates(Long concertId) {
        ConcertEntity concertEntity = concertRepo.findById(concertId).orElseThrow(RuntimeException::new);
        List<String> dates = concertEntity.getViewingDates()
            .stream().map(v -> v.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .collect(Collectors.toList());

        return ResConcertDto.builder()
            .name(concertEntity.getName())
            .availableDates(dates)
            .build();
    }

    public ResRemainSeats getRemainSeats(Long concertId, String date) {

        ViewingDateEntity viewingDateEntity = viewingDateRepo.findByConcertIdAndDate(concertId, LocalDate.parse(date))
            .orElseThrow(() -> new RuntimeException("해당 공연이 없습니다."));

        List<Integer> seatsNumber = viewingDateEntity.getSeats()
            .stream().map(SeatEntity::getSeatNumber)
            .toList();

        return ResRemainSeats.builder()
            .date(date)
            .seatNumbers(seatsNumber)
            .build();

    }

    @Transactional
    public void reserve(ReserveDto reserveDto) {

        ViewingDateEntity viewingDateEntity = viewingDateRepo.findByConcertIdAndDate(reserveDto.getConcertId(),
            LocalDate.parse(reserveDto.getDate())).orElseThrow(() -> new RuntimeException("해당 공연이 없습니다."));

        SeatEntity seatEntity = seatRepo.findByViewingDateAndSeatNumber(viewingDateEntity, reserveDto.getSeatNumber())
            .orElseThrow(() -> new RuntimeException("해당 좌석이 없습니다."));
        seatEntity.reserveSeat();

        // reserve
        UserEntity userEntity = userRepo.findById(reserveDto.getUserId())
            .orElseThrow(() -> new RuntimeException("해당 유저가 없습니다."));

        ReserveEntity reserveEntity = ReserveEntity.builder()
            .user(userEntity)
            .seat(seatEntity)
            .build();

        reserveRepo.save(reserveEntity);
    }
}
