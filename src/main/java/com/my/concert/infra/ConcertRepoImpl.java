package com.my.concert.infra;

import com.my.concert.domain.Concert;
import com.my.concert.domain.ConcertRepo;
import com.my.concert.domain.seat.infra.mysql.SeatEntity;
import com.my.concert.infra.mysql.ConcertEntity;
import com.my.concert.infra.mysql.ConcertJpaRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertRepoImpl implements ConcertRepo {

    private final ConcertJpaRepo concertJpaRepo;

    @Override
    public Concert getConcert(Long id) {
        ConcertEntity concertEntity = concertJpaRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("콘서트를 찾을 수 없습니다."));
        return Concert.builder()
            .name(concertEntity.getName())
            .startDate(concertEntity.getStartDate())
            .endDate(concertEntity.getEndDate())
            .build();
    }

    @Override
    public void saveConcert(Concert concert) {
        ConcertEntity concertEntity = concert.toEntity();

        List<SeatEntity> seatEntities = concert.getSeats()
            .stream()
            .map(seat -> seat.toEntity(concertEntity))
            .toList();

        concertEntity.addSeats(seatEntities);
        concertJpaRepo.save(concertEntity);
    }
}
