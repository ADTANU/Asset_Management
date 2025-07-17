package com.hexaware.asset.service;

import java.util.List;

import com.hexaware.asset.entity.AssetServiceRequest;

public interface AssetServiceRequestService {
    AssetServiceRequest raiseServiceRequest(AssetServiceRequest request);
    List<AssetServiceRequest> getServiceRequestsByUser(Long userId);
    AssetServiceRequest updateServiceStatus(Long id, String status);
	List<AssetServiceRequest> getAllServiceRequests();
	List<AssetServiceRequest> getRequestsByUsername(String username);
}
