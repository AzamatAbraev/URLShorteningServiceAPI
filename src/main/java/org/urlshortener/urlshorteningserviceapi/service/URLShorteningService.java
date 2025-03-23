package org.urlshortener.urlshorteningserviceapi.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntity;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityDTO;
import org.urlshortener.urlshorteningserviceapi.model.url.URLEntityRequest;
import org.urlshortener.urlshorteningserviceapi.repository.URLRepository;
import org.urlshortener.urlshorteningserviceapi.util.ShortURLGenerator;
import org.urlshortener.urlshorteningserviceapi.util.URLEntityMapper;

import java.util.Optional;

@Service
public class URLShorteningService {
    private final URLRepository urlRepository;
    private final URLEntityMapper mapper;

    @Autowired
    public URLShorteningService(URLRepository urlRepository, URLEntityMapper mapper) {
        this.urlRepository = urlRepository;
        this.mapper = mapper;
    }

    public URLEntityDTO createShortURL(@Valid URLEntityRequest urlEntityRequest) {
        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl(urlEntityRequest.getOriginalUrl());

        urlEntity = urlRepository.save(urlEntity);
        urlEntity.setShortUrl(ShortURLGenerator.encode(urlEntity.getId() + 1000));

        URLEntity savedEntity = urlRepository.save(urlEntity);
        return mapper.toDTO(savedEntity);
    }

    public URLEntityDTO getOriginalURL(String shortUrl) {
        Integer id = ShortURLGenerator.decode(shortUrl) - 1000;

        URLEntity entity = urlRepository.findById(id).orElseThrow(() -> new RuntimeException("Url is not found"));

        return mapper.toDTO(entity);
    }
}
