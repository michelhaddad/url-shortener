package io.michel.keygenerator.jobs;

import io.michel.keygenerator.config.KeysConfigurationProperties;
import io.michel.keygenerator.model.Key;
import io.michel.keygenerator.service.KeyService;
import io.michel.keygenerator.service.RandomKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FillTableWithKeys {
    private final RandomKeyGenerator randomKeyGenerator;
    private final KeyService keyService;
    private final KeysConfigurationProperties keysConfigurationProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(FillTableWithKeys.class);

    public FillTableWithKeys(final RandomKeyGenerator randomKeyGenerator, final KeyService keyService, final KeysConfigurationProperties keysConfigurationProperties) {
        this.randomKeyGenerator = randomKeyGenerator;
        this.keyService = keyService;
        this.keysConfigurationProperties = keysConfigurationProperties;
    }

    // TODO: Add rate in config file? (same for cache job)
    @Scheduled(fixedRate = 60 * 1000 * 60)
    public void fillTableWithKeys() {
        if (keyService.availableKeysCount() > keysConfigurationProperties.getAvailableKeysLimit()) {
            LOGGER.info("The maximum number of available keys has been reached. Exiting Job.");
            return;
        }
        List<String> generatedKeys = randomKeyGenerator.randomKeyBatch(10000);
        List<Key> savedKeys = keyService.addAllKeys(generatedKeys);
        LOGGER.info(String.format("Saved %d new keys to the database.", savedKeys.size()));
    }
}
