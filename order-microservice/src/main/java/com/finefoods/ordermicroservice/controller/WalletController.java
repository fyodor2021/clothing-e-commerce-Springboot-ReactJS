package com.finefoods.ordermicroservice.controller;

import com.finefoods.ordermicroservice.dto.WalletRequest;
import com.finefoods.ordermicroservice.model.CardInfo;
import com.finefoods.ordermicroservice.service.helpers.JwtService;
import com.finefoods.ordermicroservice.service.serviceImplementations.WalletServiceImp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
@Slf4j
public class WalletController {
    private final WalletServiceImp walletServiceImp;
    private final JwtService jwtService;

    @PostMapping("/add")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCard(@RequestBody WalletRequest walletRequest){
       return new ResponseEntity<>(walletServiceImp.addCard(walletRequest), HttpStatus.CREATED);
    }

    @GetMapping("/cards/{email}")
    public List<CardInfo> getCardsInfoByUserEmail(@PathVariable("email") String email){
        String hello = "hello";
        return walletServiceImp.getCardsInfoByUserEmail(email);
    }
    @DeleteMapping()
    public void deleteCard(@RequestBody Long walletId){
        walletServiceImp.deleteWallet(walletId);
    }
}
