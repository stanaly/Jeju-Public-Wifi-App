package com.jejuPublicWifi.Explore.repository;

import com.jejuPublicWifi.Explore.models.History;
import com.jejuPublicWifi.Explore.models.Review;
import com.jejuPublicWifi.Explore.models.User;
import com.jejuPublicWifi.Explore.models.Wifi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    Optional<History> findByWifi(Wifi wifi);

    Optional<History> findByUser(User user);
}
