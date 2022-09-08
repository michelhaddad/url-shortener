package io.michel.keygenerator.service;

import io.michel.keygenerator.dto.mapper.KeyMapper;
import io.michel.keygenerator.dto.model.key.KeyDto;
import io.michel.keygenerator.exception.BRSException;
import io.michel.keygenerator.exception.EntityType;
import io.michel.keygenerator.exception.ExceptionType;
import io.michel.keygenerator.model.Key;
import io.michel.keygenerator.repository.keys.KeyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.michel.keygenerator.exception.EntityType.KEY;
import static io.michel.keygenerator.exception.ExceptionType.DUPLICATE_ENTITY;

@Service
public class KeyServiceImpl implements KeyService {
    private final KeyRepository keyRepository;

    public KeyServiceImpl(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @Override
    public KeyDto addKey(final String hash) {
        Optional<Key> key = keyRepository.findById(hash);
        if (key.isPresent()) {
            throw exception(KEY, DUPLICATE_ENTITY, hash);
        }

        Key keyModel = new Key()
                .setHash(hash);

        keyRepository.save(keyModel);
        return KeyMapper.toKeyDto(keyModel);
    }

    @Override
    public List<Key> addAllKeys(final List<String> hashes) {
        Set<String> duplicateIds = keyRepository.findAllById(hashes)
                .stream()
                .map(Key::getHash)
                .collect(Collectors.toSet());

        List<Key> keysToSave = hashes
                .stream()
                .filter(hash -> !duplicateIds.contains(hash))
                .map(hash -> new Key().setHash(hash))
                .collect(Collectors.toList());

        keyRepository.saveAll(keysToSave);
        return keysToSave;
    }

    @Override
    public List<Key> fetchAndUseKeys(int count) {
        PageRequest firstKeysRequest = PageRequest.of(0, count);
        List<Key> keys = keyRepository.findByUsedFalse(firstKeysRequest);
        setKeysUsed(keys);
        return keys;
    }

    @Override
    public Key fetchAndUseSingleKey() {
        return fetchAndUseKeys(1).get(0);
    }

    @Override
    public long availableKeysCount() {
        return keyRepository.countByUsedFalse();
    }

    private void setKeysUsed(List<Key> keys) {
        for (Key key: keys) {
            key.setUsed(true);
        }
        keyRepository.saveAll(keys);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }
}
