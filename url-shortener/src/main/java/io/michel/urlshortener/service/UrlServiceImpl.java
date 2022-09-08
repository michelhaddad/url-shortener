package io.michel.urlshortener.service;

import io.michel.urlshortener.client.keygenerator.KeyGeneratorClient;
import io.michel.urlshortener.dto.mapper.UrlMapper;
import io.michel.urlshortener.dto.model.url.UrlDto;
import io.michel.urlshortener.exception.BRSException;
import io.michel.urlshortener.exception.EntityType;
import io.michel.urlshortener.exception.ExceptionType;
import io.michel.urlshortener.model.url.Url;
import io.michel.urlshortener.repository.url.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.michel.urlshortener.exception.EntityType.URL;
import static io.michel.urlshortener.exception.ExceptionType.DUPLICATE_ENTITY;
import static io.michel.urlshortener.exception.ExceptionType.ENTITY_NOT_FOUND;
import static io.michel.urlshortener.util.DateUtils.addYears;
import static io.michel.urlshortener.util.DateUtils.today;

@Service
public class UrlServiceImpl implements UrlService {
    final UrlRepository urlRepository;
    final KeyGeneratorClient keyGeneratorClient;

    public UrlServiceImpl(final UrlRepository urlRepository, final KeyGeneratorClient keyGeneratorClient) {
        this.urlRepository = urlRepository;
        this.keyGeneratorClient = keyGeneratorClient;
    }

    @Override
    public UrlDto addUrlMapping(UrlDto urlDto) {
//        TODO: handle duplicate original url
//        TODO: Handle case where duplicate hash exists but is expired

        String generatedHash = keyGeneratorClient.getRandomKey().getPayload();
        Url urlModel = new Url()
                .setOriginalUrl(urlDto.getOriginalUrl())
                .setHash(generatedHash)
                .setExpirationDate(addYears(today(), 1));

        urlRepository.save(urlModel);
        return UrlMapper.toUrlDto(urlModel);
    }

    @Override
    public UrlDto getUrlMapping(String hash) {
        Optional<Url> url = urlRepository.findById(hash);
        if (url.isEmpty()) {
            throw exception(URL, ENTITY_NOT_FOUND, hash);
        }
        return UrlMapper.toUrlDto(url.get());
    }

    @Override
    public void deleteUrlMapping(String hash) {
        Optional<Url> url = urlRepository.findById(hash);
        if (url.isEmpty()) {
            throw exception(URL, ENTITY_NOT_FOUND, hash);
        }
        urlRepository.deleteById(hash);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }
}
