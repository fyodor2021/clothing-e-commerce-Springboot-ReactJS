package com.finefoods.walletmicroservice.service;

import com.finefoods.walletmicroservice.dto.WalletRequest;
import com.finefoods.walletmicroservice.dto.WalletResponse;
import com.finefoods.walletmicroservice.model.CardInfo;
import com.finefoods.walletmicroservice.model.Wallet;
import com.finefoods.walletmicroservice.repository.CardInfoRepository;
import com.finefoods.walletmicroservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImp implements WalletService{

    private final  WalletRepository walletRepository;
    private final CardInfoRepository cardInfoRepository;

    private final Helper helper;

    @Override
    public ResponseEntity<?> addCard(WalletRequest walletRequest) {
        Wallet walletExist = walletRepository.findByCardNumber(helper.encrypt(walletRequest.getCardNumber(),"secret"));
        if (walletExist == null) {
            if (helper.varifyCard(walletRequest.getCardNumber())){
                Wallet wallet = Wallet.builder()
                        .userEmail(walletRequest.getUserEmail())
                        .cardHolderFirstName(walletRequest.getCardHolderFirstName())
                        .cardHolderLastName(walletRequest.getCardHolderLastName())
                        .cardNumber(helper.encrypt(walletRequest.getCardNumber(), "secret"))
                        .expiryDate(walletRequest.getExpiryDate())
                        .cvv(helper.encrypt(walletRequest.getCvv(), "secret"))
                        .build();
                String firstSixDigits = walletRequest.getCardNumber().substring(0,6 );
                CardInfo cardInfo = helper.getCardInfo(firstSixDigits);
                String lastFourDigits = walletRequest.getCardNumber().substring(walletRequest.getCardNumber().length() - 4);
                cardInfo.setLastFourDigit(lastFourDigits);
                cardInfo.setFirstname(walletRequest.getCardHolderFirstName());
                cardInfo.setLastname(walletRequest.getCardHolderLastName());
                cardInfo.setUserEmail(wallet.getUserEmail());
                cardInfoRepository.save(cardInfo);
                System.out.println(wallet.getCardNumber());
                walletRepository.save(wallet);
                return new ResponseEntity<>("Card added!", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Card is not Valid", HttpStatus.CONFLICT);
            }
        }else{
            return new ResponseEntity<>("Card Exists", HttpStatus.CONFLICT);
        }
    }
    public List<WalletResponse> getCardsByUserEmail(String userEmail){
        List<Wallet> cards = walletRepository.findWalletByUserEmail(userEmail);
        return cards.stream().map(wallet -> walletToWalletResponse(wallet)).toList();
    }
    public List<CardInfo> getCardsInfoByUserEmail(String userEmail){
        List<CardInfo> userCardInfo = cardInfoRepository.findByUserEmail(userEmail);
        if (userCardInfo != null){
            return userCardInfo;
        }
        return null;
    }

    public void updateWallet(Long walletId, WalletRequest walletRequest){

    }
    public void deleteWallet(Long walletId){
        Wallet walletExist = walletRepository.findWalletByWalletId(walletId);
        CardInfo cardInfoExist = cardInfoRepository.findByCardInfoId(walletId);
        if (walletExist != null && cardInfoExist != null){
            walletRepository.delete(walletExist);
            cardInfoRepository.delete(cardInfoExist);
        }
    }


    private WalletResponse walletToWalletResponse(Wallet wallet){
        return WalletResponse.builder()
                .userEmail(wallet.getUserEmail())
                .cardHolderFirstName(wallet.getCardHolderFirstName())
                .cardHolderLastName(wallet.getCardHolderLastName())
                .cardNumber(wallet.getCardNumber())
                .expiryDate(wallet.getExpiryDate())
                .cvv(wallet.getCvv())
                .build();
    }


}
