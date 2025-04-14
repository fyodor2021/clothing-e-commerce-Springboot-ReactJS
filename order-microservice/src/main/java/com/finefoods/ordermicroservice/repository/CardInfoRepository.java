package com.finefoods.ordermicroservice.repository;

import com.finefoods.ordermicroservice.model.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardInfoRepository  extends JpaRepository<CardInfo,Long> {
    List<CardInfo> findByUserEmail(String userEmail);
    CardInfo findByCardInfoId(Long cardInfoId);
    void deleteByCardInfoId(Long cardInfoId);
}
