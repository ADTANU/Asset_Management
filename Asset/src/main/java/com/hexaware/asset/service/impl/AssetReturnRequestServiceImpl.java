package com.hexaware.asset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.asset.entity.AssetReturnRequest;
import com.hexaware.asset.repository.AssetReturnRequestRepository;
import com.hexaware.asset.service.AssetReturnRequestService;

@Service
public class AssetReturnRequestServiceImpl implements AssetReturnRequestService {

    @Autowired
    private AssetReturnRequestRepository returnRepo;

    @Override
    public AssetReturnRequest submitReturnRequest(AssetReturnRequest request) {
        request.setStatus("pending");
        return returnRepo.save(request);
    }

    @Override
    public List<AssetReturnRequest> getAllRequests() {
        return returnRepo.findAll();
    }

    @Override
    public AssetReturnRequest updateRequestStatus(Long id, String status) {
        AssetReturnRequest request = returnRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Return request not found"));
        request.setStatus(status.toLowerCase());
        return returnRepo.save(request);
    }
}
