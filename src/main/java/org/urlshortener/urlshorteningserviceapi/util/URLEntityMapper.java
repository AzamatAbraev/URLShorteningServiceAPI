package org.urlshortener.urlshorteningserviceapi.util;

import org.springframework.stereotype.Component;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntity;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityDTO;

@Component
public class URLEntityMapper {

    public URLEntityDTO toDTO(URLEntity entity) {
        if (entity == null) return null;

        URLEntityDTO dto = new URLEntityDTO();
        dto.setId(entity.getId());
        dto.setOriginalUrl(entity.getOriginalUrl());
        dto.setShortUrl(entity.getShortUrl());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

}
