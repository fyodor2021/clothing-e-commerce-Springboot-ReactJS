package com.finefoods.ordermicroservice.service.serviceImplementations;

import com.finefoods.ordermicroservice.dto.WalletRequest;
import com.finefoods.ordermicroservice.dto.WalletResponse;
import com.finefoods.ordermicroservice.model.CardInfo;
import com.finefoods.ordermicroservice.model.Wallet;
import com.finefoods.ordermicroservice.repository.CardInfoRepository;
import com.finefoods.ordermicroservice.repository.WalletRepository;
import com.finefoods.ordermicroservice.service.helpers.CardHelper;
import com.finefoods.ordermicroservice.service.helpers.OrderHelper;
import com.finefoods.ordermicroservice.service.serviceInterfaces.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImp implements WalletService {

    private final  WalletRepository walletRepository;
    private final CardInfoRepository cardInfoRepository;
    private final OrderHelper orderHelper;
    private final CardHelper cardHelper;

    @Override
    public ResponseEntity<?> addCard(WalletRequest walletRequest) {
        Wallet walletExist = walletRepository.findWalletByUserEmailAndCardNumber(walletRequest.getUserEmail(),cardHelper.encrypt(walletRequest.getCardNumber(),"secret"));
        if (walletExist == null) {
            if (cardHelper.varifyCard(walletRequest.getCardNumber())){
                Wallet wallet = Wallet.builder()
                        .userEmail(walletRequest.getUserEmail())
                        .cardHolderFirstName(walletRequest.getCardHolderFirstName())
                        .cardHolderLastName(walletRequest.getCardHolderLastName())
                        .cardNumber(cardHelper.encrypt(walletRequest.getCardNumber(), "secret"))
                        .expiryDate(walletRequest.getExpiryDate())
                        .cvv(cardHelper.encrypt(walletRequest.getCvv(), "secret"))
                        .build();
                String firstSixDigits = walletRequest.getCardNumber().substring(0,6 );
                CardInfo cardInfo = cardHelper.getCardInfo(firstSixDigits);
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
        return cards.stream().map(wallet -> orderHelper.walletToWalletResponse(wallet)).toList();
    }
    public List<CardInfo> getCardsInfoByUserEmail(String userEmail){
        List<CardInfo> userCardInfo = cardInfoRepository.findByUserEmail(userEmail);
        if (userCardInfo != null){
            return userCardInfo;
        }
        return null;
    }

    public void deleteWallet(Long walletId){
        Wallet walletExist = walletRepository.findWalletByWalletId(walletId);
        CardInfo cardInfoExist = cardInfoRepository.findByCardInfoId(walletId);
        if (walletExist != null && cardInfoExist != null){
            walletRepository.delete(walletExist);
            cardInfoRepository.delete(cardInfoExist);
        }
    }





}
