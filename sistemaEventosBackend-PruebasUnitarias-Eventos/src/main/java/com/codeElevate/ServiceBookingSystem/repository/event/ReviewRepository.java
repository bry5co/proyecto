package com.codeElevate.ServiceBookingSystem.repository.event;

import com.codeElevate.ServiceBookingSystem.entity.event.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAdId(Long adId);
}
