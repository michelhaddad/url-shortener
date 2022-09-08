package io.michel.keygenerator.service;

import io.michel.keygenerator.config.KeysConfigurationProperties;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RandomKeyGeneratorImpl implements RandomKeyGenerator {
    private final RandomStringGenerator randomStringGenerator;
    private final KeysConfigurationProperties keysConfigurationProperties;

    public RandomKeyGeneratorImpl(final KeysConfigurationProperties keysConfigurationProperties) {
        this.keysConfigurationProperties = keysConfigurationProperties;
        this.randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
    }

    private String randomKey() {
        return randomStringGenerator.generate(keysConfigurationProperties.getKeyLength());
    }

    @Override
    public List<String> randomKeyBatch(final int batchSize) {
        List<String> randomKeys = new ArrayList<>();
        Set<String> keySet = new HashSet<>();

        String tmpKey;
        while (randomKeys.size() < batchSize) {
            tmpKey = randomKey();
            if (keySet.contains(tmpKey)) continue;

            keySet.add(tmpKey);
            randomKeys.add(tmpKey);
        }

        return randomKeys;
    }
}
