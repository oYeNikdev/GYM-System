package com.gymsystem.enquiry;

import java.util.regex.Pattern;

final class PhoneNumberNormalizer {
    private static final Pattern INDIAN_MOBILE = Pattern.compile("[6-9]\\d{9}");

    private PhoneNumberNormalizer() {
    }

    static String normalize(String input) {
        String compact = input.trim().replaceAll("[\\s().-]", "");
        if (compact.startsWith("+91")) {
            compact = compact.substring(3);
        } else if (compact.startsWith("91") && compact.length() == 12) {
            compact = compact.substring(2);
        } else if (compact.startsWith("0") && compact.length() == 11) {
            compact = compact.substring(1);
        }

        if (!INDIAN_MOBILE.matcher(compact).matches()) {
            throw new InvalidPhoneNumberException();
        }
        return compact;
    }
}