package com.vky20.ShrinkX.controller;

import com.vky20.ShrinkX.dto.UrlRequest;
import com.vky20.ShrinkX.dto.UrlResponse;
import com.vky20.ShrinkX.entity.Url;
import com.vky20.ShrinkX.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @Value("${app.base-url}")
    private String baseUrl;

    @PostMapping
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody UrlRequest request) {
        Url url = urlService.createShortUrl(request.getOriginalUrl(), request.getCustomAlias());

        UrlResponse response = UrlResponse.builder()
                .shortUrl(baseUrl + "/" + url.getShortCode())
                .originalUrl(url.getOriginalUrl())
                .createdAt(url.getCreatedAt())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}