package com.cms.service;

import com.cms.dto.ComplaintRequest;
import com.cms.model.Complaint;
import com.cms.model.User;
import com.cms.repository.ComplaintRepository;
import com.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    // ── Create ────────────────────────────────────────────────────────────────
    public Complaint create(ComplaintRequest req, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Complaint complaint = Complaint.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .category(req.getCategory())
                .raisedBy(userId)
                .raisedByName(user.getFullName())
                .status(Complaint.Status.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        return complaintRepository.save(complaint);
    }

    // ── Read — role-aware ─────────────────────────────────────────────────────
    public List<Complaint> getAll(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        if (user.getRole() == User.Role.FACULTY) {
            return complaintRepository.findAllByOrderByCreatedAtDesc();
        }
        return complaintRepository.findByRaisedByOrderByCreatedAtDesc(userId);
    }

    // ── Update status (Faculty only) ──────────────────────────────────────────
    public Complaint updateStatus(Long id, String status, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        if (user.getRole() != User.Role.FACULTY) {
            throw new RuntimeException("Only Faculty members can update complaint status.");
        }

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));

        // Validate status string
        Complaint.Status newStatus;
        try {
            newStatus = Complaint.Status.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                "Invalid status '" + status + "'. Allowed values: OPEN, IN_PROGRESS, RESOLVED, CLOSED"
            );
        }

        complaint.setStatus(newStatus);
        return complaintRepository.save(complaint);
    }
}
