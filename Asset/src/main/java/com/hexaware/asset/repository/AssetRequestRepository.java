package com.hexaware.asset.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.asset.entity.AssetRequest;

public interface AssetRequestRepository extends JpaRepository<AssetRequest, Long> {
    List<AssetRequest> findByUserId(Long userId);
    List<AssetRequest> findByRequestStatus(String requestStatus);
    
}