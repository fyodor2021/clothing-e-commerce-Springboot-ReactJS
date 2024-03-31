package com.finefoods.walletmicroservice.service;

import com.finefoods.walletmicroservice.dto.WalletRequest;
import com.finefoods.walletmicroservice.dto.WalletResponse;
import com.finefoods.walletmicroservice.model.CardInfo;
import com.finefoods.walletmicroservice.model.Wallet;

import java.util.List;

public interface WalletService {

    void addCard(WalletRequest walletRequest);

//    List<Wallet> getAllWallets();
    List<WalletResponse> getCardsByUserEmail(String userEmail);
    List<CardInfo> getCardsInfoByUserEmail(String userEmail);

    void updateWallet(Long walletId, WalletRequest walletRequest);
    void deleteWallet(Long walletId);




}
