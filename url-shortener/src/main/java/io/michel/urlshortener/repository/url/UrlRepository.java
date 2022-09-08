package io.michel.urlshortener.repository.url;

import io.michel.urlshortener.model.url.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, String> {
}
