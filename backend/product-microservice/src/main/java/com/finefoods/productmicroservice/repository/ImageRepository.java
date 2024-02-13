package com.finefoods.productmicroservice.repository;

import com.finefoods.productmicroservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository  extends JpaRepository<Image,Long> {
    List<Image> getImageByProductId(Long productId);
}
