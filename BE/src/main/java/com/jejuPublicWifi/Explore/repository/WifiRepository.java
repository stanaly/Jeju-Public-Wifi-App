package com.jejuPublicWifi.Explore.repository;

import com.jejuPublicWifi.Explore.models.Wifi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WifiRepository extends JpaRepository<Wifi, Long> {

  Optional<Wifi> findById(Long id);
  List<Wifi> findByAddressDetailContaining(String addressDetail);
}
