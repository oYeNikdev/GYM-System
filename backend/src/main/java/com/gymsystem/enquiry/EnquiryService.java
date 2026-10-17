package com.gymsystem.enquiry;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class EnquiryService {
    private final EnquiryRepository repository;

    public EnquiryService(EnquiryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Enquiry create(CreateEnquiryRequest request) {
        if (request.preferredDate().isBefore(LocalDate.now(ZoneId.of("Asia/Kolkata")))) {
            throw new InvalidPreferredDateException();
        }
        String name = request.name().trim().replaceAll("\\s+", " ");
        if (name.length() < 2) {
            throw new InvalidNameException();
        }
        String phone = PhoneNumberNormalizer.normalize(request.phone());
        Enquiry enquiry = new Enquiry(name, phone, request.planId(), request.preferredDate());
        return repository.save(enquiry);
    }
}