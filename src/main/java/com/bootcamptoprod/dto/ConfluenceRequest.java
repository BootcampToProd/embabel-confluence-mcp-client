package com.bootcamptoprod.dto;

/**
 * Request DTO for Confluence operations via natural language commands.
 */
public record ConfluenceRequest(
        String command
) {
}