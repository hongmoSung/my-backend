package com.my.pay.repo;

import com.my.concert.repo.SeatEntity;
import com.my.user.repo.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ReserveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reserveTime = LocalDateTime.now();

    private boolean isPaid = false;
    private int price = 5000;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userId",
            foreignKey = @ForeignKey(name = "FK_Reserve_User_Id")
    )
    private UserEntity user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "seatId",
            foreignKey = @ForeignKey(name = "FK_Seat_Id")
    )
    private SeatEntity seat;

    @Builder
    public ReserveEntity(UserEntity user, SeatEntity seat) {
        this.user = user;
        this.seat = seat;
    }
}
