package com.hexaware.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.asset.entity.*;
import com.hexaware.asset.exception.InvalidRequestException;
import com.hexaware.asset.exception.ResourceNotFoundException;
import com.hexaware.asset.service.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired private AssetService assetService;
    @Autowired private UserService userService;
    @Autowired private AssetAuditService auditService;
    @Autowired private AssetReturnRequestService returnService;
    @Autowired private AssetServiceRequestService serviceRequestService;

    @PostMapping("/add-asset")
    public ResponseEntity<Asset> addAsset(@RequestBody Asset asset) {
        if (asset == null) {
            throw new InvalidRequestException("Asset cannot be null");
        }
        return ResponseEntity.ok(assetService.addAsset(asset));
    }

    @PutMapping("/update-asset/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        if (asset == null) {
            throw new InvalidRequestException("Asset update data cannot be null");
        }
        return ResponseEntity.ok(assetService.updateAsset(id, asset));
    }

    @DeleteMapping("/delete-asset/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset deleted successfully");
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        userService.softDeleteUser(id); 
        return ResponseEntity.ok("Employee soft-deleted");
    }

    @PostMapping("/asset-category")
    public ResponseEntity<String> addCategory(@RequestParam String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidRequestException("Category name cannot be empty");
        }
        assetService.addCategory(category);
        return ResponseEntity.ok("Category added");
    }

    @GetMapping("/assigned-assets")
    public ResponseEntity<List<Asset>> getAssignedAssets() {
        List<Asset> assets = assetService.getAllAssignedAssets();
        if (assets.isEmpty()) {
            throw new ResourceNotFoundException("No assigned assets found");
        }
        return ResponseEntity.ok(assets);
    }

    @PostMapping("/audit-request")
    public ResponseEntity<String> sendAuditRequests() {
        auditService.sendAuditRequestToAllEmployees();
        return ResponseEntity.ok("Audit requests sent");
    }

    @GetMapping("/return-requests")
    public ResponseEntity<List<AssetReturnRequest>> getReturnRequests() {
        List<AssetReturnRequest> list = returnService.getAllRequests();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No return requests found");
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("/return-requests/{id}")
    public ResponseEntity<AssetReturnRequest> updateReturnRequest(
            @PathVariable Long id,
            @RequestParam String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidRequestException("Status cannot be empty");
        }
        return ResponseEntity.ok(returnService.updateRequestStatus(id, status));
    }

    @PutMapping("/update-service-status/{id}")
    public ResponseEntity<AssetServiceRequest> updateServiceRequestStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidRequestException("Status cannot be empty");
        }
        return ResponseEntity.ok(serviceRequestService.updateServiceStatus(id, status));
    }
}
