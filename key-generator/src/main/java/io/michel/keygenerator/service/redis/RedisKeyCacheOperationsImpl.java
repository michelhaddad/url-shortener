package io.michel.keygenerator.service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisKeyCacheOperationsImpl implements RedisKeyCacheOperations {
    private static final String KEYS_CACHE_ID = "available-keys";
    private final RedisTemplate<String, String> redisTemplate;

    public RedisKeyCacheOperationsImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public long keysInCacheCount() {
        Optional<Long> count = Optional.ofNullable(redisTemplate.opsForList().size(KEYS_CACHE_ID));
        if (count.isEmpty()) return 0;
        return count.get();
    }

    @Override
    public void cacheKeys(List<String> keys) {
        redisTemplate.opsForList().leftPushAll(KEYS_CACHE_ID, keys);
    }

    @Override
    public String popKey() {
        if (keysInCacheCount() == 0) throw new IllegalStateException("Cannot pop key: Keys cache is empty.");
        return redisTemplate.opsForList().leftPop(KEYS_CACHE_ID);
    }
}
