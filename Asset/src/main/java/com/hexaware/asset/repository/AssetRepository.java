package com.hexaware.asset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.asset.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByAssetCategory(String assetCategory);
    List<Asset> findByAssetStatus(String assetStatus);
    List<Asset> findByAssignedTo_UserId(Long userId); 
    List<Asset> findByAssignedToIsNotNull();
}
