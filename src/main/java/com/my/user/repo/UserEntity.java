package com.my.user.repo;

import com.my.pay.repo.WalletEntity;
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

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WalletEntity wallet;

    @Builder
    public UserEntity(String email) {
        this.email = email;
    }

    public void addWallet(WalletEntity wallet) {
        this.wallet = wallet;
    }

    public boolean isWalletEmpty() {
        return Optional.ofNullable(this.wallet).isEmpty();
    }
}
