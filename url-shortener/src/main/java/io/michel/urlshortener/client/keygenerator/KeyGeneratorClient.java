package io.michel.urlshortener.client.keygenerator;

import io.michel.urlshortener.dto.response.KeyGeneratorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "key-generator", url = "${key-generator.service.host}:${key-generator.service.port}")
public interface KeyGeneratorClient {
    @RequestMapping(method = RequestMethod.GET, value = "api/v1/key", produces = MediaType.APPLICATION_JSON_VALUE)
    KeyGeneratorResponse getRandomKey();
}
