package kr.bgmsound.service;

import kr.bgmsound.exception.ResourceAlreadyExistException;
import kr.bgmsound.exception.ResourceNotFoundException;
import kr.bgmsound.repository.TestRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public String register(String key, String value) {
        if (testRepository.findByKey(key).isPresent()) {
            throw new ResourceAlreadyExistException();
        }
        testRepository.save(key, value);
        return value;
    }

    @Override
    public String get(String key) {
        return testRepository.findByKey(key).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void remove(String key) {
        if (testRepository.findByKey(key).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        testRepository.deleteByKey(key);
    }
}
