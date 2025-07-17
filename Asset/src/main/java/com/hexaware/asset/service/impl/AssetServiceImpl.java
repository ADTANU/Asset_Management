package com.hexaware.asset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.asset.entity.Asset;
import com.hexaware.asset.repository.AssetRepository;
import com.hexaware.asset.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepo;

    @Override
    public Asset addAsset(Asset asset) {
        return assetRepo.save(asset);
    }

    @Override
    public Asset updateAsset(Long id, Asset updatedAsset) {
        Asset asset = assetRepo.findById(id).orElseThrow();
        asset.setAssetName(updatedAsset.getAssetName());
        asset.setAssetCategory(updatedAsset.getAssetCategory());
        asset.setAssetModel(updatedAsset.getAssetModel());
        asset.setManufacturingDate(updatedAsset.getManufacturingDate());
        asset.setExpiryDate(updatedAsset.getExpiryDate());
        asset.setAssetValue(updatedAsset.getAssetValue());
        return assetRepo.save(asset);
    }

    @Override
    public void deleteAsset(Long id) {
        assetRepo.deleteById(id);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    @Override
    public List<Asset> getAssetsByCategory(String category) {
        return assetRepo.findByAssetCategory(category);
    }

    @Override
    public List<Asset> getAllocatedAssets(Long userId) {
        return assetRepo.findByAssignedTo_UserId(userId); // You must implement this query method
    }

    @Override
    public List<Asset> getAllAssignedAssets() {
        return assetRepo.findByAssignedToIsNotNull(); // implement this too
    }

    @Override
    public void addCategory(String category) {
        // Store in category table if available, or log
        System.out.println("Category added: " + category);
    }

	@Override
	public Object getAssetById(long l) {
		// TODO Auto-generated method stub
		return null;
	}
}
