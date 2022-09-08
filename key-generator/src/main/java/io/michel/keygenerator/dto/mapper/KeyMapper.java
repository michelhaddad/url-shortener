package io.michel.keygenerator.dto.mapper;

import io.michel.keygenerator.dto.model.key.KeyDto;
import io.michel.keygenerator.model.Key;

public class KeyMapper {
    public static KeyDto toKeyDto(final Key key) {
        return new KeyDto()
                .setHash(key.getHash())
                .setCreationDate(key.getCreationDate());
    }
}
