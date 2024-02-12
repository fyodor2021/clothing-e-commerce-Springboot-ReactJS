package com.finefoods.walletmicroservice.service;

import com.finefoods.walletmicroservice.dto.WalletRequest;
import com.finefoods.walletmicroservice.dto.WalletResponse;
import com.finefoods.walletmicroservice.model.CardInfo;
import com.finefoods.walletmicroservice.model.Wallet;
import com.finefoods.walletmicroservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImp implements WalletService{

    private final  WalletRepository walletRepository;

    private final Helper helper;

    @Override
    public void addWallet(WalletRequest walletRequest) {
        Wallet doesExist = walletRepository.findByCardNumber(walletRequest.getCardNumber());
        if (doesExist == null) {
            if (helper.varifyCard(walletRequest.getCardNumber())){
                Wallet wallet = Wallet.builder()
                        .userId(walletRequest.getUserId())
                        .cardHolderFirstName(walletRequest.getCardHolderFirstName())
                        .cardHolderLastName(walletRequest.getCardHolderLastName())
                        .cardNumber(helper.encrypt(walletRequest.getCardNumber(), "secret"))
                        .expiryMonth(walletRequest.getExpiryMonth())
                        .expiryYear(walletRequest.getExpiryYear())
                        .cvv(helper.encrypt(walletRequest.getCvv(), "secret"))
                        .build();

                System.out.println(wallet.getCardNumber());
                walletRepository.save(wallet);
            }else {
                throw new RuntimeException("It is invalid card");
            }



        }
    }
    public List<Wallet> getAllWallets(){
        List<Wallet> wallet = walletRepository.findAll();
        return wallet;
    }

    public List<WalletResponse> getWalletsByUserId(Long userId){
        List<Wallet> cards = walletRepository.findWalletByUserId(userId);
        return cards.stream().map(wallet -> walletToWalletResponse(wallet)).toList();
    }
    public List<CardInfo> getCardsInfoByUserId(Long userId){
        List<Wallet> cards = walletRepository.findWalletByUserId(userId);
        if(cards != null){
            return cards.stream()
                    .map(wallet -> {
                        String decryptedCardNumber = helper.decrypt(wallet.getCardNumber(), "secret");
                        String firstSixDigits = decryptedCardNumber.substring(0,6 );
                        CardInfo cardInfo = helper.getCardInfo(firstSixDigits);
                        String lastFourDigits = decryptedCardNumber.substring(decryptedCardNumber.length() - 4);
                        cardInfo.setLastFourDigit(lastFourDigits);
                        return cardInfo;
                    })
                    .toList();


        }
        return null;
    }

    public void updateWallet(Long walletId, WalletRequest walletRequest){

    }
    public void deleteWallet(Long walletId){
        Wallet exist = walletRepository.findWalletByWalletId(walletId);
        if (exist != null){
            walletRepository.deleteById(walletId);
        }
    }


    private WalletResponse walletToWalletResponse(Wallet wallet){
        return WalletResponse.builder()
                .userId(wallet.getUserId())
                .cardHolderFirstName(wallet.getCardHolderFirstName())
                .cardHolderLastName(wallet.getCardHolderLastName())
                .cardNumber(wallet.getCardNumber())
                .expiryMonth(wallet.getExpiryMonth())
                .expiryYear(wallet.getExpiryYear())
                .cvv(wallet.getCvv())
                .build();
    }


}
