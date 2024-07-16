package com.finefoods.walletmicroservice.controller;

import com.finefoods.walletmicroservice.dto.WalletRequest;
import com.finefoods.walletmicroservice.dto.WalletResponse;
import com.finefoods.walletmicroservice.model.Wallet;
import com.finefoods.walletmicroservice.model.CardInfo;
import com.finefoods.walletmicroservice.service.JwtService;
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
    private final JwtService jwtService;

    @PostMapping("/add")
    public void addCard(@RequestBody WalletRequest walletRequest){
        walletServiceImp.addCard(walletRequest);
    }

//    @GetMapping
//    public List<Wallet> getAllWallets(){
//       return walletServiceImp.getAllWallets();
//    }
    @GetMapping()
    public List<WalletResponse> getCardsByUserEmail(@RequestHeader(value = "Authorization", defaultValue = "") String authHeader){
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return walletServiceImp.getCardsByUserEmail(email);

    }
    @GetMapping("/cards")
    public List<CardInfo> getCardsInfoByUserEmail(@RequestHeader(value = "Authorization", defaultValue = "") String authHeader){
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return walletServiceImp.getCardsInfoByUserEmail(email);
    }
    @DeleteMapping()
    public void deleteCard(@RequestBody Long walletId){
        walletServiceImp.deleteWallet(walletId);
    }
}
