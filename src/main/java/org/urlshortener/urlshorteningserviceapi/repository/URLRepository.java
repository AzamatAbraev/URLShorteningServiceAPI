package org.urlshortener.urlshorteningserviceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntity;

public interface URLRepository extends JpaRepository<URLEntity, Integer> {
}
