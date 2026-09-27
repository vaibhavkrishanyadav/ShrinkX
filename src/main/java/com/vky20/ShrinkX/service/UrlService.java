package com.vky20.ShrinkX.service;

import com.vky20.ShrinkX.entity.Url;
import com.vky20.ShrinkX.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public Url createShortUrl(String originalUrl, String customAlias) {
        Url url = Url.builder()
                .originalUrl(originalUrl)
                .customAlias(customAlias)
                .isActive(true)
                .clickCount(0L)
                .build();

        // Save first to get the auto-generated ID
        Url saved = urlRepository.save(url);

        // Now generate the short code from that ID
        String shortCode = encodeBase62(saved.getId());
        saved.setShortCode(shortCode);

        return urlRepository.save(saved);
    }

    private String encodeBase62(Long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % 62)));
            id /= 62;
        }
        return sb.reverse().toString();
    }

    public Url getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url;
    }
}