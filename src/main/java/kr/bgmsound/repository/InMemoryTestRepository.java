package kr.bgmsound.repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryTestRepository implements TestRepository {

    private final Map<String, String> cache = new LinkedHashMap<>();

    @Override
    public void save(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public Optional<String> findByKey(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public void deleteByKey(String key) {
        cache.remove(key);
    }
}
