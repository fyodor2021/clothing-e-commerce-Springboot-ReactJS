package com.finefoods.ordermicroservice.service.serviceInterfaces;

import com.finefoods.ordermicroservice.dto.WalletRequest;
import com.finefoods.ordermicroservice.dto.WalletResponse;
import com.finefoods.ordermicroservice.model.CardInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletService {

    ResponseEntity<?> addCard(WalletRequest walletRequest);
    List<CardInfo> getCardsInfoByUserEmail(String userEmail);
    void deleteWallet(Long walletId);
}
