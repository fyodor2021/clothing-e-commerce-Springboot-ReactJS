package com.finefoods.walletmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.Media;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_cardInfo")
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardInfoId;
    private String userEmail;
    private String bankName;
    private String firstname;
    private String lastname;
    private String brand;
    private String type;
    private Boolean isDefault;
    @Builder.Default
    private String lastFourDigit="";

}