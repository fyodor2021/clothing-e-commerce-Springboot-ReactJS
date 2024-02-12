package com.finefoods.walletmicroservice.repository;

import com.finefoods.walletmicroservice.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByCardNumber(String cardNumber);
    List<Wallet> findWalletByUserId(Long userId);
    Wallet findWalletByWalletId(Long walletId);

}
