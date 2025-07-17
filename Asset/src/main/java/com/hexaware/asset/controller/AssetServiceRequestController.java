package com.hexaware.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.asset.entity.AssetServiceRequest;
import com.hexaware.asset.exception.ResourceNotFoundException;
import com.hexaware.asset.service.AssetServiceRequestService;

@RestController
@RequestMapping("/api/service-request")
@CrossOrigin(origins = "*")
public class AssetServiceRequestController {

    @Autowired
    private AssetServiceRequestService serviceRequestService;

    @PostMapping("/raise")
    public ResponseEntity<AssetServiceRequest> raiseServiceRequest(@RequestBody AssetServiceRequest request) {
        AssetServiceRequest created = serviceRequestService.raiseServiceRequest(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<AssetServiceRequest> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        AssetServiceRequest updated = serviceRequestService.updateServiceStatus(id, status);
        if (updated == null) {
            throw new ResourceNotFoundException("Service request with ID " + id + " not found");
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssetServiceRequest>> getAllServiceRequests() {
        List<AssetServiceRequest> requests = serviceRequestService.getAllServiceRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/employee/{username}")
    public ResponseEntity<List<AssetServiceRequest>> getRequestsByUsername(@PathVariable String username) {
        List<AssetServiceRequest> requests = serviceRequestService.getRequestsByUsername(username);
        if (requests == null || requests.isEmpty()) {
            throw new ResourceNotFoundException("No service requests found for user: " + username);
        }
        return ResponseEntity.ok(requests);
    }
}
