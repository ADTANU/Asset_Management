package com.hexaware.asset.service;

import java.util.List;

import com.hexaware.asset.entity.Asset;

public interface AssetService {
	Asset addAsset(Asset asset);
    Asset updateAsset(Long id, Asset asset);
    void deleteAsset(Long id);
    List<Asset> getAllAssets();
    List<Asset> getAssetsByCategory(String category);
    List<Asset> getAllocatedAssets(Long userId);
    List<Asset> getAllAssignedAssets();
    void addCategory(String category);
	Object getAssetById(long l);
}
