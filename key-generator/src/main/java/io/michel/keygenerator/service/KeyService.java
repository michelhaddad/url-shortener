package io.michel.keygenerator.service;

import io.michel.keygenerator.dto.model.key.KeyDto;
import io.michel.keygenerator.model.Key;

import java.util.List;

public interface KeyService {
    KeyDto addKey(final String hash);

    List<Key> addAllKeys(final List<String> hashes);

    List<Key> fetchAndUseKeys(int count);

    Key fetchAndUseSingleKey();

    long availableKeysCount();
}
