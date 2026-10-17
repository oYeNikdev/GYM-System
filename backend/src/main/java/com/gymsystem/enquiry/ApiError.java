package com.gymsystem.enquiry;

import java.util.Map;

public record ApiError(String code, String message, Map<String, String> fieldErrors) {
}