// 代码生成时间: 2025-10-15 21:51:30
package com.example.web;

import org.owasp.encoder.Encode;
import java.util.function.Function;

public class XssProtectionService {

    /**
     * Sanitizes input to prevent XSS attacks by encoding potentially dangerous characters.
     *
     * @param input The input string to sanitize.
     * @return Sanitized string that is safe to display in HTML context.
     */
    public String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        return Encode.forHtml(input);
    }

    /**
     * Sanitizes output to prevent XSS attacks when reflecting user input back to the browser.
     *
     * @param output The output string to sanitize.
     * @return Sanitized string that is safe to include in HTML content.
     */
    public String sanitizeOutput(String output) {
        if (output == null) {
            return null;
        }
        return Encode.forHtmlContent(output);
    }

    /**
     * Applies the XSS protection to a string using a provided function.
     *
     * @param input The input string to protect.
     * @param function A Function that takes a String and returns a String.
     * @return The result of applying the function to the input string.
     */
    public String applyXssProtection(String input, Function<String, String> function) {
        try {
            // Apply the function to the sanitized input
            return function.apply(sanitizeInput(input));
        } catch (Exception e) {
            // Handle any exceptions that may occur during the sanitization process
            throw new IllegalArgumentException("Failed to apply XSS protection: " + e.getMessage(), e);
        }
    }
}
