package com.bootcamptoprod.dto;

import java.util.Map;

/**
 * Response DTO for Confluence operations containing results and metadata.
 */
public record ConfluenceResponse(
        Map<String, Object> confluenceMetadata,
        String message,
        boolean success,
        String operationType
) {
}