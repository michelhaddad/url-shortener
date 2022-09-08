package io.michel.urlshortener.dto.mapper;

import io.michel.urlshortener.dto.model.url.UrlDto;
import io.michel.urlshortener.model.url.Url;

public class UrlMapper {
    public static UrlDto toUrlDto(Url url) {
        return new UrlDto()
                .setOriginalUrl(url.getOriginalUrl())
                .setHash(url.getHash())
                .setExpirationDate(url.getExpirationDate())
                .setCreationDate(url.getCreationDate());
    }
}
