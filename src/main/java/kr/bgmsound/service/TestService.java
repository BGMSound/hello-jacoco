package kr.bgmsound.service;

public interface TestService {

    String register(String key, String value);

    String get(String key);

    void remove(String key);

}
