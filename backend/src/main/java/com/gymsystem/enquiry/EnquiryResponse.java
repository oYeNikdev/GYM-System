package com.gymsystem.enquiry;

import java.time.Instant;

public record EnquiryResponse(Long id, String status, Instant createdAt, String message) {
}