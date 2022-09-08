package io.michel.keygenerator.service;

import java.util.List;

public interface RandomKeyGenerator {
    List<String> randomKeyBatch(final int batchSize);
}
