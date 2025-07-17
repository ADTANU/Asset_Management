package com.hexaware.asset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.asset.entity.AssetServiceRequest;
import com.hexaware.asset.repository.AssetServiceRequestRepository;
import com.hexaware.asset.service.AssetServiceRequestService;

@Service
public class AssetServiceRequestServiceImpl implements AssetServiceRequestService {

    @Autowired
    private AssetServiceRequestRepository serviceRequestRepo;

    @Override
    public AssetServiceRequest raiseServiceRequest(AssetServiceRequest request) {
        request.setStatus("Pending");
        return serviceRequestRepo.save(request);
    }

    @Override
    public List<AssetServiceRequest> getServiceRequestsByUser(Long userId) {
        return serviceRequestRepo.findByUserId(userId);
    }
    
    @Override
    public AssetServiceRequest updateServiceStatus(Long id, String status) {
        AssetServiceRequest req = serviceRequestRepo.findById(id).orElseThrow();
        req.setStatus(status);
        return serviceRequestRepo.save(req);
    }

	@Override
	public List<AssetServiceRequest> getAllServiceRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetServiceRequest> getRequestsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
