package com.finefoods.productmicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_image")
@Data
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long imageId;
    String imageFileName;
    Long productId;
}
