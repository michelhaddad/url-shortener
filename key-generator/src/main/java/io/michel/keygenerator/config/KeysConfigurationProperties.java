package io.michel.keygenerator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("keys")
public class KeysConfigurationProperties {

    private int cachedKeysLimit;
    private int keyLength;
    private int availableKeysLimit;

    public int getAvailableKeysLimit() {
        return availableKeysLimit;
    }

    public void setAvailableKeysLimit(int availableKeysLimit) {
        this.availableKeysLimit = availableKeysLimit;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public int getCachedKeysLimit() {
        return cachedKeysLimit;
    }

    public void setCachedKeysLimit(int cachedKeysLimit) {
        this.cachedKeysLimit = cachedKeysLimit;
    }
}