package com.cms.service;

import com.cms.dto.ComplaintRequest;
<<<<<<< HEAD
import com.cms.model.Complaint;
import com.cms.model.User;
import com.cms.repository.ComplaintRepository;
import com.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

=======
import com.cms.model.*;
import com.cms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
>>>>>>> 06559c1 (Initial commit for Render deployment)
import java.util.List;

@Service
public class ComplaintService {

<<<<<<< HEAD
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    // ── Create ────────────────────────────────────────────────────────────────
    public Complaint create(ComplaintRequest req, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Complaint complaint = new Complaint();
        complaint.setTitle(req.getTitle());
        complaint.setDescription(req.getDescription());
        complaint.setCategory(req.getCategory());
        complaint.setRaisedBy(userId);
        complaint.setRaisedByName(user.getFullName());
        complaint.setStatus(Complaint.Status.OPEN);

        return complaintRepository.save(complaint);
    }

    // ── Read — role-aware ─────────────────────────────────────────────────────
    public List<Complaint> getAll(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        if (user.getRole() == User.Role.FACULTY) {
            // Faculty sees all complaints
            return complaintRepository.findAllByOrderByCreatedAtDesc();
        }
        // Students see only their own
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

        // Guard against invalid status strings to avoid 500 errors
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
=======
    @Autowired private ComplaintRepository complaintRepository;
    @Autowired private UserRepository userRepository;

    public Complaint create(ComplaintRequest req, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint c = Complaint.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .category(req.getCategory())
                .raisedBy(userId)
                .raisedByName(user.getFullName())
                .status(Complaint.Status.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        return complaintRepository.save(c);
    }

    public List<Complaint> getAll(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == User.Role.FACULTY)
            return complaintRepository.findAllByOrderByCreatedAtDesc();

        return complaintRepository.findByRaisedByOrderByCreatedAtDesc(userId);
    }

    public Complaint updateStatus(Long id, String status, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != User.Role.FACULTY)
            throw new RuntimeException("Only Faculty can update complaint status");

        Complaint c = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        c.setStatus(Complaint.Status.valueOf(status.toUpperCase()));
        return complaintRepository.save(c);
    }
}
>>>>>>> 06559c1 (Initial commit for Render deployment)
