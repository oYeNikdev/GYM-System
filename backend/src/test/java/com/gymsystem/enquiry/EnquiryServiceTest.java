package com.gymsystem.enquiry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnquiryServiceTest {
    @Mock
    private EnquiryRepository repository;

    @InjectMocks
    private EnquiryService service;

    @Test
    @SuppressWarnings("null")
    void normalizesAndPersistsOnlySubmittedEnquiryFields() {
        LocalDate preferredDate = LocalDate.now(ZoneId.of("Asia/Kolkata")).plusDays(2);
        when(repository.save(org.mockito.ArgumentMatchers.any(Enquiry.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Enquiry saved = service.create(new CreateEnquiryRequest(
                "  Test   Member ", "+91 98765-43210", "monthly", preferredDate));

        ArgumentCaptor<Enquiry> captor = ArgumentCaptor.forClass(Enquiry.class);
        verify(repository).save(captor.capture());
        assertEquals("Test Member", captor.getValue().getName());
        assertEquals("9876543210", captor.getValue().getPhone());
        assertEquals("monthly", captor.getValue().getPreferredPlan());
        assertEquals(preferredDate, captor.getValue().getPreferredDate());
        assertEquals("NEW", saved.getStatus());
    }

    @Test
    void rejectsPastPreferredDateBeforePersistence() {
        LocalDate pastDate = LocalDate.now(ZoneId.of("Asia/Kolkata")).minusDays(1);
        assertThrows(InvalidPreferredDateException.class, () -> service.create(new CreateEnquiryRequest(
                "Test Member", "9876543210", "monthly", pastDate)));
    }

    @Test
    void rejectsSingleCharacterNameAfterTrimming() {
        assertThrows(InvalidNameException.class, () -> service.create(new CreateEnquiryRequest(
                " A ", "9876543210", "monthly", LocalDate.now(ZoneId.of("Asia/Kolkata")))));
        verifyNoInteractions(repository);
    }
}