package io.michel.keygenerator.service.redis;

import java.util.List;

public interface RedisKeyCacheOperations {
    long keysInCacheCount();

    void cacheKeys(List<String> keys);

    String popKey();
}
