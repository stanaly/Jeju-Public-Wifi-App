package com.jejuPublicWifi.Explore.repository;

import com.jejuPublicWifi.Explore.models.FnQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FnQRepository extends JpaRepository<FnQ, Long> {

}
