package kr.bgmsound.repository;

import java.util.Optional;

public interface TestRepository {

    void save(String key, String value);

    Optional<String> findByKey(String key);

    void deleteByKey(String key);

}
