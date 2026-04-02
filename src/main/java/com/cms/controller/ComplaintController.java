package com.cms.controller;

import com.cms.dto.ApiResponse;
import com.cms.dto.ComplaintRequest;
import com.cms.model.Complaint;
import com.cms.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "http://localhost:5173")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // Create a new complaint
    @PostMapping
    public ResponseEntity<ApiResponse<Complaint>> create(
            @Valid @RequestBody ComplaintRequest request,
            Authentication authentication) {

        String userId = (authentication != null) ? authentication.getName() : "anonymous";
        Complaint created = complaintService.create(request, userId);

        return ResponseEntity.ok(
                ApiResponse.success("Complaint submitted successfully", created)
        );
    }

    // Get complaints (faculty: all, student: own)
    @GetMapping
    public ResponseEntity<ApiResponse<List<Complaint>>> getAll(
            Authentication authentication) {

        String userId = (authentication != null) ? authentication.getName() : "anonymous";
        List<Complaint> complaints = complaintService.getAll(userId);

        return ResponseEntity.ok(
                ApiResponse.success("Complaints fetched successfully", complaints)
        );
    }

    // Update complaint status (faculty only)
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Complaint>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            Authentication authentication) {

        String userId = (authentication != null) ? authentication.getName() : "anonymous";
        Complaint updated = complaintService.updateStatus(id, status, userId);

        return ResponseEntity.ok(
                ApiResponse.success("Complaint status updated to " + updated.getStatus(), updated)
        );
    }
}
