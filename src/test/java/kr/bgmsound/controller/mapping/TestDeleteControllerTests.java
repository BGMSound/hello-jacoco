package kr.bgmsound.controller.mapping;

import kr.bgmsound.exception.ResourceNotFoundException;
import kr.bgmsound.service.TestService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDeleteControllerTests {

    private static TestService testService;
    private static TestDeleteController testDeleteController;

    @BeforeAll
    public static void setUp() {
        testService = mock();
        testDeleteController = new TestDeleteController(testService);
    }

    @Test
    public void testDelete() {
        doNothing().when(testService).remove("key");
        testDeleteController.testDelete(List.of("key"));
        verify(testService).remove("key");

        doThrow(ResourceNotFoundException.class).when(testService).remove("key");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> testDeleteController.testDelete(Collections.emptyList()));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> testDeleteController.testDelete(List.of("key", "value")));
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> testDeleteController.testDelete(List.of("key")));
    }
}
