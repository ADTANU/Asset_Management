package com.hexaware.asset.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.asset.entity.AssetRequest;
import com.hexaware.asset.repository.AssetRequestRepository;
import com.hexaware.asset.service.AssetRequestService;

@Service
public class AssetRequestServiceImpl implements AssetRequestService {

    @Autowired
    private AssetRequestRepository requestRepo; // ✅ Use only one repo reference

    @Override
    public AssetRequest requestAsset(AssetRequest request) {
        request.setRequestDate(LocalDate.now());
        request.setRequestStatus("Pending");

        // Save request
        AssetRequest savedRequest = requestRepo.save(request);

        // Fetch full object with relationships (if needed for response)
        return requestRepo.findById(savedRequest.getRequestId()).orElse(null);
    }

    @Override
    public List<AssetRequest> getRequestsByUser(Long userId) {
        return requestRepo.findByUserId(userId);
    }
}
