package com.vky20.ShrinkX.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vky20.ShrinkX.entity.UrlAnalytics;

@Repository
public interface UrlAnalyticsRepository extends JpaRepository<UrlAnalytics, Long> {
    
    List<UrlAnalytics> findByUrlId(Long urlId);
    
    @Query("SELECT COUNT(a) FROM UrlAnalytics a WHERE a.url.id = :urlId")
    Long countByUrlId(@Param("urlId") Long urlId);
    
    @Query("SELECT a FROM UrlAnalytics a WHERE a.url.id = :urlId AND a.clickedAt BETWEEN :start AND :end")
    List<UrlAnalytics> findByUrlIdAndDateRange(
        @Param("urlId") Long urlId, 
        @Param("start") LocalDateTime start, 
        @Param("end") LocalDateTime end
    );
}
