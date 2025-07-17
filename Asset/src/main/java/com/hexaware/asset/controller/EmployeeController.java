package com.hexaware.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.asset.entity.*;
import com.hexaware.asset.exception.ResourceNotFoundException;
import com.hexaware.asset.service.*;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired private AssetService assetService;
    @Autowired private AssetRequestService requestService;
    @Autowired private AssetServiceRequestService serviceRequestService;
    @Autowired private AssetAuditService auditService;
    @Autowired private AssetReturnRequestService returnService;

    @GetMapping("/assets")
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        if (assets.isEmpty()) {
            throw new ResourceNotFoundException("No assets found");
        }
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/assets/category/{category}")
    public ResponseEntity<List<Asset>> getByCategory(@PathVariable String category) {
        List<Asset> assets = assetService.getAssetsByCategory(category);
        if (assets.isEmpty()) {
            throw new ResourceNotFoundException("No assets found in category: " + category);
        }
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/allocated-assets/{userId}")
    public ResponseEntity<List<Asset>> getAllocatedAssets(@PathVariable Long userId) {
        List<Asset> assets = assetService.getAllocatedAssets(userId);
        if (assets.isEmpty()) {
            throw new ResourceNotFoundException("No allocated assets found for user ID: " + userId);
        }
        return ResponseEntity.ok(assets);
    }

    @PostMapping("/request-asset")
    public ResponseEntity<AssetRequest> requestAsset(@RequestBody AssetRequest request) {
        AssetRequest result = requestService.requestAsset(request);
        if (result == null) {
            throw new RuntimeException("Failed to request asset");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/raise-service")
    public ResponseEntity<AssetServiceRequest> raiseServiceRequest(@RequestBody AssetServiceRequest request) {
        AssetServiceRequest result = serviceRequestService.raiseServiceRequest(request);
        if (result == null) {
            throw new RuntimeException("Failed to raise service request");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/audit")
    public ResponseEntity<AssetAuditRequest> auditAsset(@RequestBody AssetAuditRequest request) {
        AssetAuditRequest result = auditService.submitAudit(request);
        if (result == null) {
            throw new RuntimeException("Failed to submit audit request");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/return-asset")
    public ResponseEntity<AssetReturnRequest> returnAsset(@RequestBody AssetReturnRequest request) {
        AssetReturnRequest result = returnService.submitReturnRequest(request);
        if (result == null) {
            throw new RuntimeException("Failed to submit return request");
        }
        return ResponseEntity.ok(result);
    }
}
