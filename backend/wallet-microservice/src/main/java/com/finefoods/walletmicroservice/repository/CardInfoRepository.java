package com.finefoods.walletmicroservice.repository;

import com.finefoods.walletmicroservice.model.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardInfoRepository  extends JpaRepository<CardInfo,Long> {
    List<CardInfo> findByUserEmail(String userEmail);
}
