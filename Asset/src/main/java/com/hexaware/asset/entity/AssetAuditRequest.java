package com.hexaware.asset.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "asset_audit_requests")
public class AssetAuditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditRequestId;

    private Long assetId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", insertable = false, updatable = false)
    private User assignedTo;  // Reference to the User entity

    private String auditStatus; // Pending, Verified, Rejected
    private LocalDate requestDate;
    private LocalDate responseDate;

    // Getters and Setters
    public Long getAuditRequestId() {
        return auditRequestId;
    }

    public void setAuditRequestId(Long auditRequestId) {
        this.auditRequestId = auditRequestId;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    // Removed userId field

    public User getAssignedTo() {
        return assignedTo; // Getter for assignedTo (User)
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo; // Setter for assignedTo (User)
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDate responseDate) {
        this.responseDate = responseDate;
    }
}
