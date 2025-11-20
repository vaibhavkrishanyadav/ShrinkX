package com.vky20.ShrinkX.repository;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vky20.ShrinkX.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    
    Optional<Url> findByShortCode(String shortCode);
    
    Optional<Url> findByCustomAlias(String customAlias);
    
    boolean existsByShortCode(String shortCode);
    
    boolean existsByCustomAlias(String customAlias);
    
    @Query("SELECT u FROM Url u WHERE u.isActive = true AND (u.expiresAt IS NULL OR u.expiresAt > :now)")
    List<Url> findAllActiveUrls(@Param("now") LocalDateTime now);
}
