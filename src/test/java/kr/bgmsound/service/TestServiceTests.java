package kr.bgmsound.service;

import kr.bgmsound.exception.ResourceAlreadyExistException;
import kr.bgmsound.exception.ResourceNotFoundException;
import kr.bgmsound.repository.TestRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestServiceTests {

    private static TestRepository testRepository;
    private static TestService testService;

    @BeforeAll
    public static void setUp() {
        testRepository = mock();
        testService = new TestServiceImpl(testRepository);
    }

    @Test
    public void testRegister() {
        when(testRepository.findByKey("key")).thenReturn(Optional.empty()).thenReturn(Optional.of("value"));

        assertThat(testService.register("key", "value")).isEqualTo("value");
        assertThatExceptionOfType(ResourceAlreadyExistException.class)
                .isThrownBy(() -> testService.register("key", "value"));
    }

    @Test
    public void testGet() {
        when(testRepository.findByKey("key")).thenReturn(Optional.of("value")).thenReturn(Optional.empty());

        assertThat(testService.get("key")).isEqualTo("value");
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> testService.get("key"));
    }

    @Test
    public void testDelete() {
        when(testRepository.findByKey("key")).thenReturn(Optional.of("value")).thenReturn(Optional.empty());

        testService.remove("key");
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> testService.remove("key"));
    }
}
