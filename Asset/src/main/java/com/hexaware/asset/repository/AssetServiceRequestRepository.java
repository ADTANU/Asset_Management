package com.hexaware.asset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.asset.entity.AssetServiceRequest;

public interface AssetServiceRequestRepository extends JpaRepository<AssetServiceRequest, Long> {
    List<AssetServiceRequest> findByUserId(Long userId);
    List<AssetServiceRequest> findByStatus(String status);
}