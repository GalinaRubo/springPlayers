package com.pro.springPlayers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.springPlayers.models.Ratio;

@Repository
public interface RatioRepository  extends JpaRepository<Ratio, Long> {
	List<Ratio> findRatiosByRateId(Long rateId);
}
