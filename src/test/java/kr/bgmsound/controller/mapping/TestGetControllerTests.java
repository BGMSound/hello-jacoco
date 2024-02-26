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
public class TestGetControllerTests {

    private static TestService testService;
    private static TestGetController testGetController;

    @BeforeAll
    public static void setUp() {
        testService = mock();
        testGetController = new TestGetController(testService);
    }

    @Test
    public void testGet() {
        when(testService.get("key")).thenReturn("value").thenThrow(ResourceNotFoundException.class);
        testGetController.testGet(List.of("key"));

        verify(testService).get("key");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> testGetController.testGet(Collections.emptyList()));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> testGetController.testGet(List.of("key", "value")));
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> testGetController.testGet(List.of("key")));
    }
}
