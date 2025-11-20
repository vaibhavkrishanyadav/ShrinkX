package com.vky20.ShrinkX.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_analytics", indexes = {
    @Index(name = "idx_url_id", columnList = "url_id"),
    @Index(name = "idx_clicked_at", columnList = "clicked_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlAnalytics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id", nullable = false)
    private Url url;
    
    @Column(name = "clicked_at", nullable = false)
    private LocalDateTime clickedAt;
    
    @Column(name = "ip_address", length = 45)
    private String ipAddress;
    
    @Column(name = "user_agent")
    private String userAgent;
    
    @Column(name = "referer")
    private String referer;
    
    @Column(name = "country", length = 100)
    private String country;
    
    @Column(name = "device_type", length = 50)
    private String deviceType;
    
    @PrePersist
    protected void onCreate() {
        clickedAt = LocalDateTime.now();
    }
}