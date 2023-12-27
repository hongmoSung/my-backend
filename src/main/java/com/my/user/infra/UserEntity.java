package com.my.user.infra;

import com.my.user.domain.pay.infra.mysql.PayEntity;
import jakarta.persistence.*;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_User_Email",
                        columnNames = "email"
                )
        }
)
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String email;

    @OneToOne(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private PayEntity pay;

    @Builder
    public UserEntity(String email) {
        this.email = email;
    }

    public void addPay(PayEntity pay) {
        this.pay = pay;
    }

    public boolean isPayEmpty() {
        return Optional.ofNullable(this.pay).isEmpty();
    }
}
