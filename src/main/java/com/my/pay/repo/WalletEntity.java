package com.my.pay.repo;

import com.my.user.repo.UserEntity;
import jakarta.persistence.*;
import java.math.BigInteger;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "wallet")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigInteger money = BigInteger.ZERO;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        foreignKey = @ForeignKey(name = "FK_Wallet_User_Id")
    )
    private UserEntity user;

    @Builder
    public WalletEntity(UserEntity user) {
        this.user = user;
    }

    public void rechargeMoney(BigInteger chargeAmount) {
        this.money = this.money.add(chargeAmount);
    }

    public void payMoney(int price) {
        this.money = this.money.subtract(BigInteger.valueOf(price));
    }
}
