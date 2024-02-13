package com.finefoods.walletmicroservice.controller;

import com.finefoods.walletmicroservice.dto.WalletRequest;
import com.finefoods.walletmicroservice.dto.WalletResponse;
import com.finefoods.walletmicroservice.model.Wallet;
import com.finefoods.walletmicroservice.model.CardInfo;
import com.finefoods.walletmicroservice.service.WalletServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
@Slf4j
public class WalletController {
    private final WalletServiceImp walletServiceImp;


    @PostMapping
    public void addCard(@RequestBody WalletRequest walletRequest){
        walletServiceImp.addWallet(walletRequest);
    }

    @GetMapping
    public List<Wallet> getAllWallets(){
       return walletServiceImp.getAllWallets();
    }
    @GetMapping("/{userId}")
    public List<WalletResponse> getWalletsByUserId(@PathVariable  Long userId){
        return walletServiceImp.getWalletsByUserId(userId);

    }

    @GetMapping("/cards/{userId}")
    public List<CardInfo> getCardsInfoByUserId(@PathVariable  Long userId){
        return walletServiceImp.getCardsInfoByUserId(userId);
    }

}
