package com.my.concert.repo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "seat")
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seatNumber;

    private boolean isReserved = false;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "viewingDateId",
            foreignKey = @ForeignKey(name = "FK_Viewing_Date_Id")
    )
    private ViewingDateEntity viewingDate;

    @Builder
    public SeatEntity(int seatNumber, ViewingDateEntity viewingDate) {
        this.seatNumber = seatNumber;
        this.viewingDate = viewingDate;
    }

    public void reserveSeat() {
        this.isReserved = true;
    }
}
