package com.jejuPublicWifi.Explore.repository;

import com.jejuPublicWifi.Explore.models.Review;
import com.jejuPublicWifi.Explore.models.User;
import com.jejuPublicWifi.Explore.models.Wifi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByWifi(Wifi wifi);
    Optional<Review> findByUser(User user);
}
