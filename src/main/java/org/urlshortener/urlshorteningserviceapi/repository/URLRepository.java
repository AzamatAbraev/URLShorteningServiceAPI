package org.urlshortener.urlshorteningserviceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface URLRepository extends JpaRepository<URLEntity, Integer> {
    List<URLEntity> findByExpiresAtBefore(LocalDateTime expirationTimeBefore);

}
