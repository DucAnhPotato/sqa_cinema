package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
