package com.finefoods.walletmicroservice.service;

import com.finefoods.walletmicroservice.dto.WalletRequest;
import com.finefoods.walletmicroservice.dto.WalletResponse;
import com.finefoods.walletmicroservice.model.CardInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletService {

    ResponseEntity<Object> addCard(WalletRequest walletRequest);

//    List<Wallet> getAllWallets();
    List<WalletResponse> getCardsByUserEmail(String userEmail);
    List<CardInfo> getCardsInfoByUserEmail(String userEmail);

    void updateWallet(Long walletId, WalletRequest walletRequest);
    void deleteWallet(Long walletId);




}
