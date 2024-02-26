package kr.bgmsound.controller;

import kr.bgmsound.controller.mapping.TestDeleteController;
import kr.bgmsound.controller.mapping.TestGetController;
import kr.bgmsound.controller.mapping.TestPostController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TestDispatcherController {

    private final TestGetController testGetController;
    private final TestPostController testPostController;
    private final TestDeleteController testDeleteController;

    public void testDispatch(String commandLine) {
        List<String> commandLineParser = List.of(commandLine.split(" "));
        if (commandLineParser.isEmpty()) {
            log.error("Invalid command: {}", commandLine);
            throw new IllegalArgumentException("Invalid command: " + commandLine);
        }

        String command = commandLineParser.get(0);
        List<String> arguments = commandLineParser.subList(1, commandLineParser.size());

        switch (command) {
            case "GET" -> testGetController.testGet(arguments);
            case "POST" -> testPostController.testPost(arguments);
            case "DELETE" -> testDeleteController.testDelete(arguments);
            default -> throw new IllegalArgumentException("Invalid command: " + command);
        }
    }
}
