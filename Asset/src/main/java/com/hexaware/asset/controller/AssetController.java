package com.hexaware.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.hexaware.asset.config.JwtService;
import com.hexaware.asset.entity.*;
import com.hexaware.asset.service.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AssetController {

    @Autowired private UserService userService;
    @Autowired private AssetService assetService;
    @Autowired private AssetRequestService assetRequestService;
    @Autowired private AssetServiceRequestService serviceRequestService;
    @Autowired private AssetAuditService assetAuditService;
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtService jwtService;
    @Autowired private AssetReturnRequestService assetReturnRequestService;

    @GetMapping("/employee/assets")
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/employee/assets/category/{category}")
    public ResponseEntity<List<Asset>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(assetService.getAssetsByCategory(category));
    }

    @PostMapping("/employee/request-asset")
    public ResponseEntity<AssetRequest> requestAsset(@RequestBody AssetRequest request) {
        return ResponseEntity.ok(assetRequestService.requestAsset(request));
    }

    @PostMapping("/employee/raise-service")
    public ResponseEntity<AssetServiceRequest> raiseServiceRequest(@RequestBody AssetServiceRequest request) {
        return ResponseEntity.ok(serviceRequestService.raiseServiceRequest(request));
    }

    @PostMapping("/employee/audit")
    public ResponseEntity<AssetAuditRequest> auditAsset(@RequestBody AssetAuditRequest auditRequest) {
        System.out.println("Received audit request: " + auditRequest);
        AssetAuditRequest savedRequest = assetAuditService.submitAudit(auditRequest);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/employee/allocated-assets/{userId}")
    public ResponseEntity<List<Asset>> getAllocatedAssets(@PathVariable Long userId) {
        return ResponseEntity.ok(assetService.getAllocatedAssets(userId));
    }
    @PostMapping("/admin/add-asset")
    public ResponseEntity<Asset> addAsset(@RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.addAsset(asset));
    }

    @PutMapping("/admin/update-asset/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.updateAsset(id, asset));
    }

    @DeleteMapping("/admin/delete-asset/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset deleted");
    }

    @PostMapping("/admin/audit-request")
    public ResponseEntity<String> sendAuditRequestsToAll() {
        assetAuditService.sendAuditRequestToAllEmployees();
        return ResponseEntity.ok("Audit requests sent to all employees");
    }

    @PutMapping("/admin/update-service-status/{id}")
    public ResponseEntity<AssetServiceRequest> updateServiceStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(serviceRequestService.updateServiceStatus(id, status));
    }

    @GetMapping("/admin/assigned-assets")
    public ResponseEntity<List<Asset>> getAllAssignedAssets() {
        return ResponseEntity.ok(assetService.getAllAssignedAssets());
    }

    @PostMapping("/admin/asset-category")
    public ResponseEntity<String> addCategory(@RequestParam String category) {
        assetService.addCategory(category);
        return ResponseEntity.ok("Category added successfully");
    }

    @DeleteMapping("/admin/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok("Employee soft-deleted (deactivated)");
    }
    @PostMapping("/employee/return-asset")
    public ResponseEntity<AssetReturnRequest> returnAsset(@RequestBody AssetReturnRequest request) {
        return ResponseEntity.ok(assetReturnRequestService.submitReturnRequest(request));
    }
    @GetMapping("/admin/return-requests")
    public ResponseEntity<List<AssetReturnRequest>> viewReturnRequests() {
        return ResponseEntity.ok(assetReturnRequestService.getAllRequests());
    }
    @PutMapping("/admin/return-requests/{id}")
    public ResponseEntity<AssetReturnRequest> updateReturnStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(assetReturnRequestService.updateRequestStatus(id, status));
    }

    
}
