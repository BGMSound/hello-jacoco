package kr.bgmsound.controller.mapping;

import kr.bgmsound.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TestGetController {

    private final TestService testService;

    public void testGet(List<String> arguments) {
        int length = arguments.size();
        if (length != 1) {
            log.error("Invalid arguments: {}", arguments);
            throw new IllegalArgumentException("Invalid arguments: " + arguments);
        }

        String key = arguments.get(0);
        testService.get(key);
        log.info("Found: {}", key);
    }

}
