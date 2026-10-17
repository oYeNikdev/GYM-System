package com.gymsystem.enquiry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnquiryController.class)
@Import(com.gymsystem.WebConfiguration.class)
class EnquiryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnquiryService service;

    @Test
    void createsEnquiryAndReturnsSafeReceipt() throws Exception {
        Enquiry saved = mock(Enquiry.class);
        when(saved.getId()).thenReturn(42L);
        when(saved.getStatus()).thenReturn("NEW");
        when(saved.getCreatedAt()).thenReturn(LocalDateTime.of(2026, 10, 17, 12, 0));
        when(service.create(any())).thenReturn(saved);

        mockMvc.perform(post("/api/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody("+91 98765-43210")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(42))
                .andExpect(jsonPath("$.status").value("NEW"))
                .andExpect(jsonPath("$.message").value("Your enquiry has been received."))
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.phone").doesNotExist());
    }

    @Test
    void rejectsInvalidPhoneWithStructuredSafeError() throws Exception {
        when(service.create(any())).thenThrow(new InvalidPhoneNumberException());

        mockMvc.perform(post("/api/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody("123")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.fieldErrors.phone").exists())
                .andExpect(jsonPath("$.stackTrace").doesNotExist());
    }

    @Test
    void rejectsUnknownFieldsAndDoesNotCallService() throws Exception {
        String body = requestBody("9876543210").replace("}", ",\"email\":\"person@example.com\"}");

        mockMvc.perform(post("/api/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_REQUEST"));
        verify(service, never()).create(any());
    }

    @Test
    void allowsConfiguredFrontendOriginOnPreflight() throws Exception {
        mockMvc.perform(options("/api/enquiries")
                        .header("Origin", "http://localhost:8000")
                        .header("Access-Control-Request-Method", "POST")
                        .header("Access-Control-Request-Headers", "content-type"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
                        .header().string("Access-Control-Allow-Origin", "http://localhost:8000"));
    }

    private String requestBody(String phone) {
        return """
                {"name":"Test Member","phone":"%s","planId":"monthly","preferredDate":"%s"}
                """.formatted(phone, LocalDate.now(ZoneOffset.UTC).plusDays(1));
    }
}