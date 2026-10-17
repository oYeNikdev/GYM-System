package com.gymsystem.enquiry;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {
    private final EnquiryService service;

    public EnquiryController(EnquiryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnquiryResponse> create(@Valid @RequestBody CreateEnquiryRequest request) {
        Enquiry saved = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new EnquiryResponse(saved.getId(), saved.getStatus(), saved.getCreatedAt().toInstant(ZoneOffset.UTC),
                        "Your enquiry has been received."));
    }
}