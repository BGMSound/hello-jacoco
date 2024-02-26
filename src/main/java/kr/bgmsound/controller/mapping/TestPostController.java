package kr.bgmsound.controller.mapping;

import kr.bgmsound.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TestPostController {

    private final TestService testService;

    public void testPost(List<String> arguments) {
        int length = arguments.size();
        if (length < 2) {
            log.error("Invalid arguments: {}", arguments);
            throw new IllegalArgumentException("Invalid arguments: " + arguments);
        }

        String key = arguments.get(0);
        String value = String.join(" ", arguments.subList(1, length));
        testService.register(key, value);
        log.info("Registered: {} {}", key, value);
    }
}
