package com.hexaware.asset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.asset.entity.AssetReturnRequest;

public interface AssetReturnRequestRepository extends JpaRepository<AssetReturnRequest, Long> {
    List<AssetReturnRequest> findByUserId(Long userId);
    List<AssetReturnRequest> findByStatus(String status); // ✅ This is valid
}
