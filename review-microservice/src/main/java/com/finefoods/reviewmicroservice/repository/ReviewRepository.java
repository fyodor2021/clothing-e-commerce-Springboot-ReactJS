package com.finefoods.reviewmicroservice.repository;

import com.finefoods.reviewmicroservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
