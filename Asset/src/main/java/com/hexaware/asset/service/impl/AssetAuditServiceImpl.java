package com.hexaware.asset.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.asset.entity.AssetAuditRequest;
import com.hexaware.asset.repository.AssetAuditRequestRepository;
import com.hexaware.asset.service.AssetAuditService;

@Service
public class AssetAuditServiceImpl implements AssetAuditService {

    @Autowired
    private AssetAuditRequestRepository auditRequestRepository;
    @Override
    public AssetAuditRequest submitAudit(AssetAuditRequest auditRequest) {
        // Set initial audit status and request date
        auditRequest.setAuditStatus("Pending");
        auditRequest.setRequestDate(LocalDate.now());
        return auditRequestRepository.save(auditRequest);
    }

    
    @Override
    public void sendAuditRequestToAllEmployees() {
        // For demo: Create audit requests for all assigned assets to all employees
        // This is just a placeholder. You should fetch employees and their assigned assets to create requests.
    }
    @Override
    public List<AssetAuditRequest> getAllAuditRequests() {
        return auditRequestRepository.findAll();
    }


    @Override
    public AssetAuditRequest updateAuditStatus(Long auditRequestId, String status) {
        AssetAuditRequest existingRequest = auditRequestRepository.findById(auditRequestId)
            .orElseThrow(() -> new RuntimeException("Audit request not found with id: " + auditRequestId));
        
        existingRequest.setAuditStatus(status);
        existingRequest.setResponseDate(LocalDate.now());
        return auditRequestRepository.save(existingRequest);
    }


	@Override
	public List<AssetAuditRequest> getAuditRequestsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
