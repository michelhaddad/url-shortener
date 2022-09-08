package io.michel.keygenerator.jobs;

import io.michel.keygenerator.config.KeysConfigurationProperties;
import io.michel.keygenerator.model.Key;
import io.michel.keygenerator.service.KeyService;
import io.michel.keygenerator.service.redis.RedisKeyCacheOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class FillCacheWithKeys {


    private final RedisKeyCacheOperations redisKeyCacheOperations;
    private static final Logger LOGGER = LoggerFactory.getLogger(FillTableWithKeys.class);
    private final KeyService keyService;
    private final KeysConfigurationProperties keysConfigurationProperties;

    public FillCacheWithKeys(final RedisKeyCacheOperations redisKeyCacheOperations, final KeyService keyService, final KeysConfigurationProperties keysConfigurationProperties) {
        this.redisKeyCacheOperations = redisKeyCacheOperations;
        this.keyService = keyService;
        this.keysConfigurationProperties = keysConfigurationProperties;
    }

    @Scheduled(cron = "0 * * * * *")
    public void fillCacheWithKeys() {
        final int keysInCache = (int) redisKeyCacheOperations.keysInCacheCount();
        if (keysInCache >= keysConfigurationProperties.getCachedKeysLimit()) {
            LOGGER.info("The number of cached keys has reached the maximum. No more keys will be added.");
            return;
        }

        long keysToFetch = keysConfigurationProperties.getCachedKeysLimit() - keysInCache;
        List<String> availableKeys = keyService.fetchAndUseKeys((int) keysToFetch)
                .stream()
                .map(Key::getHash)
                .collect(Collectors.toList());
        if (availableKeys.isEmpty()) {
            LOGGER.info("Found no available keys to add to cache. Exiting job.");
        }
        redisKeyCacheOperations.cacheKeys(availableKeys);
        LOGGER.info(String.format("%d keys were added to the cache.", availableKeys.size()));
    }
}
