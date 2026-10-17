package com.gymsystem.enquiry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateEnquiryRequest(
        @NotBlank(message = "Enter your name.")
        @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters.")
        String name,

        @NotBlank(message = "Enter your mobile number.")
        @Size(max = 30, message = "Enter a valid Indian mobile number.")
        String phone,

        @NotBlank(message = "Choose a plan.")
        @Pattern(regexp = "monthly|quarterly|annual", message = "Choose one of the available plans.")
        String planId,

        @NotNull(message = "Choose a visit date.")
        LocalDate preferredDate) {
}