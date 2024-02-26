package kr.bgmsound.controller.mapping;

import kr.bgmsound.exception.ResourceAlreadyExistException;
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
public class TestPostControllerTests {

    private static TestService testService;
    private static TestPostController testPostController;

    @BeforeAll
    public static void setUp() {
        testService = mock();
        testPostController = new TestPostController(testService);
    }

    @Test
    public void testPost() {
        when(testService.register("key", "value")).thenReturn("value").thenThrow(ResourceAlreadyExistException.class);
        testPostController.testPost(List.of("key", "value"));

        verify(testService).register("key", "value");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> testPostController.testPost(Collections.emptyList()));
        assertThatExceptionOfType(ResourceAlreadyExistException.class).isThrownBy(() -> testPostController.testPost(List.of("key", "value")));
    }
}
