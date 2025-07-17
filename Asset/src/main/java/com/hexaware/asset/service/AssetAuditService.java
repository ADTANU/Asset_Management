package com.hexaware.asset.service;

import java.util.List;

import com.hexaware.asset.entity.AssetAuditRequest;

public interface AssetAuditService {
	AssetAuditRequest submitAudit(AssetAuditRequest auditRequest);
    
    // Send audit requests to all employees (admin side)
    void sendAuditRequestToAllEmployees();
    
    // Fetch all audit requests
    List<AssetAuditRequest> getAllAuditRequests();
    
    // Update audit request status by admin (approve/reject)
    AssetAuditRequest updateAuditStatus(Long auditRequestId, String status);

	List<AssetAuditRequest> getAuditRequestsByUsername(String username);
}
