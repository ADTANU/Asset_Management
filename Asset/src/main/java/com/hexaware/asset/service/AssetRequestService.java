package com.hexaware.asset.service;

import java.util.List;

import com.hexaware.asset.entity.AssetRequest;

public interface AssetRequestService {
    AssetRequest requestAsset(AssetRequest request);
    List<AssetRequest> getRequestsByUser(Long userId);
}
