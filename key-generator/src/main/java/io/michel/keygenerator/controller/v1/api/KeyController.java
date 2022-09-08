package io.michel.keygenerator.controller.v1.api;

import com.sun.istack.NotNull;
import io.michel.keygenerator.dto.response.Response;
import io.michel.keygenerator.service.KeyService;
import io.michel.keygenerator.service.redis.RedisKeyCacheOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/key")
public class KeyController {
    private final RedisKeyCacheOperations redisKeyCacheOperations;
    private final KeyService keyService;

    public KeyController(final RedisKeyCacheOperations redisKeyCacheOperations, final KeyService keyService) {
        this.redisKeyCacheOperations = redisKeyCacheOperations;
        this.keyService = keyService;
    }

    @GetMapping
    public Response<String> getRandomKey() {
        String key = fetchAvailableKey();
        return Response.<String>ok().setPayload(key);
    }

    @NotNull
    private String fetchAvailableKey() {
        if (redisKeyCacheOperations.keysInCacheCount() > 0) return redisKeyCacheOperations.popKey();

        return keyService.fetchAndUseSingleKey().getHash();
    }
}
