package com.gymsystem.enquiry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneNumberNormalizerTest {
    @Test
    void normalizesIndianMobileFormats() {
        assertEquals("9876543210", PhoneNumberNormalizer.normalize("9876543210"));
        assertEquals("9876543210", PhoneNumberNormalizer.normalize("+91 98765-43210"));
        assertEquals("9876543210", PhoneNumberNormalizer.normalize("  +91 98765-43210  "));
        assertEquals("9876543210", PhoneNumberNormalizer.normalize("09876543210"));
    }

    @Test
    void rejectsInvalidIndianMobileNumbers() {
        assertThrows(InvalidPhoneNumberException.class,
                () -> PhoneNumberNormalizer.normalize("+1 9876543210"));
        assertThrows(InvalidPhoneNumberException.class,
                () -> PhoneNumberNormalizer.normalize("5876543210"));
    }
}