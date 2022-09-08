package io.michel.urlshortener.service;

import io.michel.urlshortener.dto.model.url.UrlDto;
import io.michel.urlshortener.repository.url.UrlRepository;
import org.springframework.stereotype.Service;


public interface UrlService {

    UrlDto addUrlMapping(UrlDto urlDto);

    UrlDto getUrlMapping(String hash);

    void deleteUrlMapping(String hash);
}
