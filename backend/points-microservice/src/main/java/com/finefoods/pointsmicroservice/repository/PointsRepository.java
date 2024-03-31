package com.finefoods.pointsmicroservice.repository;

import com.finefoods.pointsmicroservice.dto.PointsResponse;
import com.finefoods.pointsmicroservice.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, Long> {
    Points findPointsByPointsId(Long pointsId);
    Points findPointsByUserEmail(String userEmail);




}
