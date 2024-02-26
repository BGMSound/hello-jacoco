package kr.bgmsound.controller;

import kr.bgmsound.controller.mapping.TestDeleteController;
import kr.bgmsound.controller.mapping.TestGetController;
import kr.bgmsound.controller.mapping.TestPostController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDispatcherControllerTests {

    private static TestGetController testGetController;
    private static TestPostController testPostController;
    private static TestDeleteController testDeleteController;
    private static TestDispatcherController testDispatcherController;

    @BeforeAll
    public static void setUp() {
        testGetController = mock();
        testPostController = mock();
        testDeleteController = mock();
        testDispatcherController = new TestDispatcherController(testGetController, testPostController, testDeleteController);
    }

    @ParameterizedTest
    @ValueSource(strings = {"GET key", "POST key value", "DELETE key", "PATCH key value", ""})
    public void testDispatch(String command) {
        if (command.isEmpty()) {
            assertThrows(IllegalArgumentException.class, () -> testDispatcherController.testDispatch(command));
            return;
        }
        assertThrows(IllegalArgumentException.class, () -> testDispatcherController.testDispatch(" "));

        String method = command.split(" ")[0];
        if (method.equals("PATCH")) {
            assertThrows(IllegalArgumentException.class, () -> testDispatcherController.testDispatch(command));
            return;
        }
        testDispatcherController.testDispatch(command);
        switch (method) {
            case "GET" -> verify(testGetController).testGet(anyList());
            case "POST" -> verify(testPostController).testPost(anyList());
            case "DELETE" -> verify(testDeleteController).testDelete(anyList());
        }
    }

}
