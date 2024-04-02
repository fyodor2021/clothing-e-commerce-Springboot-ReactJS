package com.finefoods.walletmicroservice.service;

import com.finefoods.walletmicroservice.dto.WalletRequest;
import com.finefoods.walletmicroservice.dto.WalletResponse;
import com.finefoods.walletmicroservice.model.CardInfo;
import com.finefoods.walletmicroservice.model.Wallet;
import com.finefoods.walletmicroservice.repository.CardInfoRepository;
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
    private final CardInfoRepository cardInfoRepository;

    private final Helper helper;

    @Override
    public void addCard(WalletRequest walletRequest) {
        Wallet doesExist = walletRepository.findByCardNumber(walletRequest.getCardNumber());
        if (doesExist == null) {
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
            }else {
                throw new RuntimeException("It is invalid card");
            }
        }
    }
//    public List<Wallet> getAllWallets(){
//        List<Wallet> wallet = walletRepository.findAll();
//        return wallet;
//    }

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
        Wallet exist = walletRepository.findWalletByWalletId(walletId);
        if (exist != null){
            walletRepository.deleteById(walletId);
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
