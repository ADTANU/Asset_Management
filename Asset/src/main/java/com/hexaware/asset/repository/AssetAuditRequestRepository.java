package com.hexaware.asset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.asset.entity.Asset;
import com.hexaware.asset.entity.AssetAuditRequest;

public interface AssetAuditRequestRepository extends JpaRepository<AssetAuditRequest, Long> {
    List<AssetAuditRequest> findByAssignedTo_UserId(Long userId);
}
