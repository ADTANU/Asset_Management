package com.hexaware.asset.service;

import java.util.List;
import com.hexaware.asset.entity.AssetReturnRequest;

public interface AssetReturnRequestService {
    AssetReturnRequest submitReturnRequest(AssetReturnRequest request);
    List<AssetReturnRequest> getAllRequests();
    AssetReturnRequest updateRequestStatus(Long id, String status);
}
