package com.hexaware.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.asset.entity.AssetAuditRequest;
import com.hexaware.asset.exception.ResourceNotFoundException;
import com.hexaware.asset.service.AssetAuditService;

@RestController
@RequestMapping("/api/audit")
@CrossOrigin(origins = "*")
public class AssetAuditController {

    @Autowired
    private AssetAuditService auditService;

    @PostMapping("/send-request")
    public ResponseEntity<String> sendAuditRequestsToAll() {
        auditService.sendAuditRequestToAllEmployees();
        return ResponseEntity.ok("Audit requests sent to all employees");
    }

    @PostMapping("/submit")
    public ResponseEntity<AssetAuditRequest> submitAudit(@RequestBody AssetAuditRequest auditRequest) {
        AssetAuditRequest submitted = auditService.submitAudit(auditRequest);
        if (submitted == null) {
            throw new ResourceNotFoundException("Failed to submit audit request.");
        }
        return ResponseEntity.ok(submitted);
    }
    @GetMapping("/employee/{username}")
    public ResponseEntity<List<AssetAuditRequest>> getAuditRequestsForEmployee(@PathVariable String username) {
        List<AssetAuditRequest> requests = auditService.getAuditRequestsByUsername(username);
        if (requests == null || requests.isEmpty()) {
            throw new ResourceNotFoundException("No audit requests found for employee: " + username);
        }
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssetAuditRequest>> getAllAuditRequests() {
        List<AssetAuditRequest> all = auditService.getAllAuditRequests();
        if (all == null || all.isEmpty()) {
            throw new ResourceNotFoundException("No audit requests found in the system.");
        }
        return ResponseEntity.ok(all);
    }
}
