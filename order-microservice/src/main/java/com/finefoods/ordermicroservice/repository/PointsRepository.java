package com.finefoods.ordermicroservice.repository;

import com.finefoods.ordermicroservice.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, Long> {
    Points findPointsByPointsId(Long pointsId);
    Points findPointsByUserEmail(String userEmail);




}
