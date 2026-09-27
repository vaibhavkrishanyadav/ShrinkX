package com.vky20.ShrinkX.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class UrlResponse {
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime createdAt;
}