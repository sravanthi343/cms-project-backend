package com.cms.controller;

<<<<<<< HEAD
import com.cms.dto.ApiResponse;
import com.cms.dto.ComplaintRequest;
=======
import com.cms.dto.*;
>>>>>>> 06559c1 (Initial commit for Render deployment)
import com.cms.model.Complaint;
import com.cms.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD

=======
>>>>>>> 06559c1 (Initial commit for Render deployment)
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
<<<<<<< HEAD
=======
@CrossOrigin(origins = "http://localhost:5173")
>>>>>>> 06559c1 (Initial commit for Render deployment)
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

<<<<<<< HEAD
    /**
     * POST /api/complaints
     * Requires: Bearer token (any authenticated user)
     *
     * Body:
     * {
     *   "title": "WiFi not working in lab",
     *   "description": "The WiFi in Computer Lab 3 has been down for 2 days.",
     *   "category": "Infrastructure"
     * }
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Complaint>> create(
            @Valid @RequestBody ComplaintRequest request,
            Authentication authentication) {

        String userId = authentication.getName();
        Complaint created = complaintService.create(request, userId);
        return ResponseEntity.ok(
            ApiResponse.success("Complaint submitted successfully", created)
        );
    }

    /**
     * GET /api/complaints
     * Requires: Bearer token
     * - Faculty  → returns ALL complaints
     * - Student  → returns only their own complaints
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Complaint>>> getAll(
            Authentication authentication) {

        String userId = authentication.getName();
        List<Complaint> complaints = complaintService.getAll(userId);
        return ResponseEntity.ok(
            ApiResponse.success("Complaints fetched successfully", complaints)
        );
    }

    /**
     * PUT /api/complaints/{id}/status?status=IN_PROGRESS
     * Requires: Bearer token (Faculty only)
     *
     * Allowed status values: OPEN, IN_PROGRESS, RESOLVED, CLOSED
     */
=======
    @PostMapping
    public ResponseEntity<ApiResponse<Complaint>> create(
            @Valid @RequestBody ComplaintRequest req,
            Authentication auth) {

        String user = (auth != null) ? auth.getName() : "anonymous";

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Complaint submitted",
                        complaintService.create(req, user)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Complaint>>> getAll(
            Authentication auth) {

        String user = (auth != null) ? auth.getName() : "anonymous";

        return ResponseEntity.ok(
                ApiResponse.success(
                        "OK",
                        complaintService.getAll(user)
                )
        );
    }

>>>>>>> 06559c1 (Initial commit for Render deployment)
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Complaint>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
<<<<<<< HEAD
            Authentication authentication) {

        String userId = authentication.getName();
        Complaint updated = complaintService.updateStatus(id, status, userId);
        return ResponseEntity.ok(
            ApiResponse.success("Complaint status updated to " + updated.getStatus(), updated)
        );
    }
}
=======
            Authentication auth) {

        String user = (auth != null) ? auth.getName() : "anonymous";

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Updated",
                        complaintService.updateStatus(id, status, user)
                )
        );
    }
}
>>>>>>> 06559c1 (Initial commit for Render deployment)
