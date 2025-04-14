package com.finefoods.ordermicroservice.repository;

import com.finefoods.ordermicroservice.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByCardNumber(String cardNumber);
    List<Wallet> findWalletByUserEmail(String userEmail);
    Wallet findWalletByWalletId(Long walletId);
}
