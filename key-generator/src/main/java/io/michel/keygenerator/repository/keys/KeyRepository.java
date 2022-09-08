package io.michel.keygenerator.repository.keys;

import io.michel.keygenerator.model.Key;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyRepository extends JpaRepository<Key, String> {
    List<Key> findByUsedFalse(Pageable pageable);

    long countByUsedFalse();
}
