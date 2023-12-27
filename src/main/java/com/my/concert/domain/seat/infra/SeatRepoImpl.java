package com.my.concert.domain.seat.infra;

import static com.my.concert.domain.seat.infra.mysql.QSeatEntity.seatEntity;
import static com.querydsl.core.types.ExpressionUtils.count;

import com.my.concert.domain.seat.api.dto.ResEnableSeat;
import com.my.concert.domain.seat.api.dto.ResRemainSeats;
import com.my.concert.domain.seat.domain.SeatRepo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatRepoImpl implements SeatRepo {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResRemainSeats> getEnableReserveDays(Long concertId) {
        return queryFactory.select(
                Projections.constructor(ResRemainSeats.class,
                    seatEntity.date,
                    count(seatEntity.id)
                )
            )
            .from(seatEntity)
            .where(
                seatEntity.concert.id.eq(concertId)
                    .and(seatEntity.isReserved.isFalse())
            )
            .groupBy(seatEntity.date)
            .fetch();
    }

    @Override
    public List<ResEnableSeat> getRemainSeatsByDate(Long concertId, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return queryFactory.select(
                Projections.constructor(ResEnableSeat.class,
                    seatEntity.id,
                    seatEntity.no
                )
            )
            .from(seatEntity)
            .where(
                seatEntity.concert.id.eq(concertId)
                    .and(seatEntity.date.eq(date))
                    .and(seatEntity.isReserved.isFalse())
            )
            .fetch();
    }
}
